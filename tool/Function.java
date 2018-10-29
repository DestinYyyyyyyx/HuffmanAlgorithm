package tool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Function {

	//获取tools工具
	Tools tools=null;
	
	//构造函数
	public Function() {
		super();
		tools=new Tools();
	}
	
	//哈夫曼树根节点
	public HufTreeNode root=new HufTreeNode();
	//构造哈夫曼树
	/*
	 * queue中升序存放了文本中各字符出现的次数及其它相关信息的节点
	 * 依次从queue中取出两个节点，生成一个新的父亲节点
	 * 将父亲节点放入queue中，直到只剩下一个节点为止，此节点即为_Huffman树的root节点
	 * 
     */
	@SuppressWarnings("static-access")
	public void createHuffmanTree()
	{
		boolean flag=true;
		if(tools.queue.size()==1)
		{
			root.element=tools.queue.peek().element;
			root.rate=tools.queue.peek().rate;
			root.code="0";
			flag=false;
			return ;
		}
		
		
		while(tools.queue.size()>1 && flag==true)
		{
			//取出两个节点
			HufTreeNode node1=tools.queue.poll();
			HufTreeNode node2=tools.queue.poll();
			
			//生成的父亲节点
			HufTreeNode cur=new HufTreeNode();
			cur.rate=node1.rate+node2.rate;
			cur.element='#';
			cur.left=node1;
			cur.right=node2;
			node1.parent=node2.parent=cur;
			//将父亲加入queue
			tools.queue.add(cur);
			root=cur;
		}
		
		root.element=tools.queue.peek().element;
		root.rate=tools.queue.peek().rate;
		root.code="";
	}

	//获取每个节点的哈夫曼编码
	/*
	 * 利用前序遍历获取编码表
	 * 若向左，则加'0';若向右，则加'1';遇到叶子节点则返回其code值
	 * 
	 */
	@SuppressWarnings("static-access")
	public void getHuffmanCode(HufTreeNode cur,String code)
	{
		if(cur!=null)
		{
			if(cur.left==null && cur.right==null)
			{
				cur.code=code;
				tools.array[cur.element-' ']=code;
			}
			getHuffmanCode(cur.left,code+"0");
			getHuffmanCode(cur.right,code+"1");
		}
	}
	
	//将二进制编码信息保存到临时压缩文件
	@SuppressWarnings("static-access")
	public void createTempFile()
	{
		Reader sourcereader=null;
		//保存二进制信息的字符串
		String string="";
		try {
			sourcereader=new InputStreamReader(new FileInputStream(tools.sourcefilename));
			//文件中的单个字符对应的整形数值
			int returnValue;
			try {
				while((returnValue=sourcereader.read())!=-1)
				{
					FileWriter writer=null;
					writer=new FileWriter(tools.compressfilename,true);
					char character=(char)returnValue;
					//获取二进制信息
					 string+=tools.array[character-' '];
					//每八位转换成一个字符
					while(string.length()>8)
					{
						//每八位转换成一个字符
						writer.write(StringToChar(string.substring(0, 8)));
						//截取新串
						string=string.substring(8);
						//更新
						writer.flush();
						writer.close();
						
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					sourcereader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//不足八位的补成八位
		if(string.length()%8!=0)
		{
			FileWriter writer=null;
			try {
				writer=new FileWriter(tools.compressfilename,true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			tools.zero=8-string.length();
			for(int i=0;i<tools.zero;i++)
				string+='0';
			try {
				//每八位转换成一个字符
				writer.write(StringToChar(string.substring(0, 8)));
				//截取新串
				string=string.substring(8);
				//更新
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//删除临时压缩文件
		if(tools.tempfile.exists())
			tools.tempfile.delete();
	}
	
	//每八位转换成一个字符
	public char StringToChar(String str)
	{
		
		char character = 0;
		int m = 1;
		for (int i = 7; i >= 0; i--)
		{
			character = (char)(character + (str.charAt(i) - '0')*m);
			m = m * 2;
		}
		return character;
	}

}
