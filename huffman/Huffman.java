package huffman;
import java.io.File;
import java.io.IOException;
import getSourceFileMessage.*;
import tool.*;

public class Huffman {

	//��ȡ_tool����tools����
	tool.Tools tools=null;
	//��ȡ_tool����function����
	tool.Function function=null;
	
	//���캯��
	public Huffman() {
		super();
		tools=new tool.Tools();
		function=new Function();
	}
	
	//ѹ���ļ�
	@SuppressWarnings("static-access")
	public void SourceFileToHuffmanFile() {
		
		//��ȡĿ���ļ���Ϣ����ȡ����������ṹ
		GetSourceFile source=new GetSourceFile();
		source.getSourceFile();
		//���ɹ�������
		function.createHuffmanTree();
		//��ȡÿ���ڵ�Ĺ���������
		function.getHuffmanCode(function.root,function.root.code);
		//����ѹ���ļ���
		tools.compressfilename=tools.sourcefilename.replace(".txt", "(ѹ���ļ�).txt");
		//������ʱѹ���ļ�(��������Ʊ�����Ϣ)
				tools.tempfile=new File(tools.sourcefilename.replace(".txt", "(��ʱѹ���ļ�).txt"));
		try {
			tools.tempfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//�������Ʊ�����Ϣ��������ʱѹ���ļ�
		function.createTempFile();
		//�ļ�ѹ�����
		System.out.println("�ļ�ѹ����ɣ�");
		
	}
	

}
