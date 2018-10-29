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
	
	//��ȡ_tools����tools����
	tool.Tools tools=null;
	
	//���캯��
	public GetSourceFile() {
		super();
		this.tools=new tool.Tools();
	}
	
	//��ȡ�ļ���ϸ��Ϣ
	public void getSourceFile() 
	{
		//��ȡ�ļ�·��
		getSourceFilePath();
		//��ȡ�ļ�����
		getSourceFileContent();
	}
	
	//��ȡ��Ҫѹ�����ļ�·��
		@SuppressWarnings("static-access")
		private boolean getSourceFilePath()
		{
			System.out.println("����Ҫѹ�����ļ�·����");
			//��ȡ�ļ�·��
			@SuppressWarnings("resource")
			Scanner scanner=new Scanner(System.in);
			tools.sourcefilename=scanner.nextLine();
			File file=new File(tools.sourcefilename);
			if(file.exists()) {
				System.out.println("����׼��ѹ��������");
			}else {
				System.out.println("�ļ������ڻ���ʧЧ��");
			}
			return true;
		}
	
	//��ȡ��Ҫѹ�����ļ�����
	@SuppressWarnings({ "static-access", "unchecked" })
	private void getSourceFileContent() 
	{
		Reader reader=null;
		File file=new File(tools.sourcefilename);
		try {
			reader=new InputStreamReader(new FileInputStream(file));
			try {
				
				//�ļ��еĵ����ַ���Ӧ��������ֵ
				int returnValue;
				//�ַ����ı��г��ֵĴ���
				int value=0;
				//�ַ����ı��г��ֵĴ���ת�����ַ�������
				String strValue;
				//�ַ�ת�����ַ�������
				String strKey;
				while((returnValue=reader.read())!=-1)
				{
					//��������ת���������ַ�
					char character=(char)returnValue;
					//���ַ�ת��Ϊ�ַ�������
					strKey=String.valueOf(character);
					//�жϸ��ַ��Ƿ����
					if(tools.map.containsKey(strKey)) {
						value+=1;
					}else {
						value=1;
					}
					//���ַ����ֵĴ���ת��Ϊ�ַ�������
					strValue=Integer.toString(value);
					tools.map.put(strKey,strValue);
				}
				
				//��TreeMap�е��ַ���Ϣ���浽queue��
				@SuppressWarnings("rawtypes")
				Iterator iter = tools.map.entrySet().iterator();
				while(iter.hasNext()) {
					
				    @SuppressWarnings("rawtypes")
					Map.Entry entry = (Map.Entry)iter.next();
					
					//�½�һ�����������ڵ�
					HufTreeNode node=new HufTreeNode();
					
					//�ڵ�����
					node.element=((String) entry.getKey()).charAt(0);
					node.rate=Integer.parseInt((String) entry.getValue());
					node.left=node.right=node.parent=null;
					
					//����ڵ�
					tools.queue.add(node);
				}
				
				//�ر��ļ�����ֹ�ļ���Ϣ��ʧ
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		
	}
	
}