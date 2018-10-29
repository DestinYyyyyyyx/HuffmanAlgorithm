package tool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Function {

	//��ȡtools����
	Tools tools=null;
	
	//���캯��
	public Function() {
		super();
		tools=new Tools();
	}
	
	//�����������ڵ�
	public HufTreeNode root=new HufTreeNode();
	//�����������
	/*
	 * queue�����������ı��и��ַ����ֵĴ��������������Ϣ�Ľڵ�
	 * ���δ�queue��ȡ�������ڵ㣬����һ���µĸ��׽ڵ�
	 * �����׽ڵ����queue�У�ֱ��ֻʣ��һ���ڵ�Ϊֹ���˽ڵ㼴Ϊ_Huffman����root�ڵ�
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
			//ȡ�������ڵ�
			HufTreeNode node1=tools.queue.poll();
			HufTreeNode node2=tools.queue.poll();
			
			//���ɵĸ��׽ڵ�
			HufTreeNode cur=new HufTreeNode();
			cur.rate=node1.rate+node2.rate;
			cur.element='#';
			cur.left=node1;
			cur.right=node2;
			node1.parent=node2.parent=cur;
			//�����׼���queue
			tools.queue.add(cur);
			root=cur;
		}
		
		root.element=tools.queue.peek().element;
		root.rate=tools.queue.peek().rate;
		root.code="";
	}

	//��ȡÿ���ڵ�Ĺ���������
	/*
	 * ����ǰ�������ȡ�����
	 * ���������'0';�����ң����'1';����Ҷ�ӽڵ��򷵻���codeֵ
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
	
	//�������Ʊ�����Ϣ���浽��ʱѹ���ļ�
	@SuppressWarnings("static-access")
	public void createTempFile()
	{
		Reader sourcereader=null;
		//�����������Ϣ���ַ���
		String string="";
		try {
			sourcereader=new InputStreamReader(new FileInputStream(tools.sourcefilename));
			//�ļ��еĵ����ַ���Ӧ��������ֵ
			int returnValue;
			try {
				while((returnValue=sourcereader.read())!=-1)
				{
					FileWriter writer=null;
					writer=new FileWriter(tools.compressfilename,true);
					char character=(char)returnValue;
					//��ȡ��������Ϣ
					 string+=tools.array[character-' '];
					//ÿ��λת����һ���ַ�
					while(string.length()>8)
					{
						//ÿ��λת����һ���ַ�
						writer.write(StringToChar(string.substring(0, 8)));
						//��ȡ�´�
						string=string.substring(8);
						//����
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
		
		//�����λ�Ĳ��ɰ�λ
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
				//ÿ��λת����һ���ַ�
				writer.write(StringToChar(string.substring(0, 8)));
				//��ȡ�´�
				string=string.substring(8);
				//����
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//ɾ����ʱѹ���ļ�
		if(tools.tempfile.exists())
			tools.tempfile.delete();
	}
	
	//ÿ��λת����һ���ַ�
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
