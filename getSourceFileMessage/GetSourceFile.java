package getSourceFileMessage;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import tool.HufTreeNode;

public class GetSourceFile {
	
	//获取_tools包的tools工具
	tool.Tools tools=null;
	
	//构造函数
	public GetSourceFile() {
		super();
		this.tools=new tool.Tools();
	}
	
	//获取文件详细信息
	public void getSourceFile() 
	{
		//读取文件路径
		getSourceFilePath();
		//读取文件内容
		getSourceFileContent();
	}
	
	//获取想要压缩的文件路径
		@SuppressWarnings("static-access")
		private boolean getSourceFilePath()
		{
			System.out.println("输入要压缩的文件路径：");
			//读取文件路径
			@SuppressWarnings("resource")
			Scanner scanner=new Scanner(System.in);
			tools.sourcefilename=scanner.nextLine();
			File file=new File(tools.sourcefilename);
			if(file.exists()) {
				System.out.println("正在准备压缩。。。");
			}else {
				System.out.println("文件不存在或已失效！");
			}
			return true;
		}
	
	//获取想要压缩的文件内容
	@SuppressWarnings({ "static-access", "unchecked" })
	private void getSourceFileContent() 
	{
		Reader reader=null;
		File file=new File(tools.sourcefilename);
		try {
			reader=new InputStreamReader(new FileInputStream(file));
			try {
				
				//文件中的单个字符对应的整形数值
				int returnValue;
				//字符在文本中出现的次数
				int value=0;
				//字符在文本中出现的次数转换成字符串类型
				String strValue;
				//字符转换成字符串类型
				String strKey;
				while((returnValue=reader.read())!=-1)
				{
					//由整形数转换而来的字符
					char character=(char)returnValue;
					//将字符转换为字符串类型
					strKey=String.valueOf(character);
					//判断该字符是否出现
					if(tools.map.containsKey(strKey)) {
						value+=1;
					}else {
						value=1;
					}
					//将字符出现的次数转换为字符串类型
					strValue=Integer.toString(value);
					tools.map.put(strKey,strValue);
				}
				
				//将TreeMap中的字符信息保存到queue中
				@SuppressWarnings("rawtypes")
				Iterator iter = tools.map.entrySet().iterator();
				while(iter.hasNext()) {
					
				    @SuppressWarnings("rawtypes")
					Map.Entry entry = (Map.Entry)iter.next();
					
					//新建一个哈夫曼树节点
					HufTreeNode node=new HufTreeNode();
					
					//节点属性
					node.element=((String) entry.getKey()).charAt(0);
					node.rate=Integer.parseInt((String) entry.getValue());
					node.left=node.right=node.parent=null;
					
					//插入节点
					tools.queue.add(node);
				}
				
				//关闭文件，防止文件信息丢失
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		
	}
	
}