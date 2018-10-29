package tool;

import java.io.File;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
 * �����õ��ĸ������ݽṹ������
 */
public class Tools {

	//array�����С
	private static int MAXN=1000000;
	
	//���캯��
	public Tools() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//���ȶ������ڱ���Ҫѹ�����ĵ��Ĺ��������ṹ
	public static PriorityQueue<HufTreeNode> queue=new PriorityQueue<HufTreeNode>(
			new Comparator<HufTreeNode>() {
				public int compare(HufTreeNode node1,HufTreeNode node2) {
						      return node1.rate-node2.rate;
				}
			});
	
	//��TreeMap��¼�ַ��������
	//TODO�Ż� : ��map�е�Ԫ�ذ�valueС������
	@SuppressWarnings("rawtypes")
	public static TreeMap map=new TreeMap<String,String>();
	
	//array��¼�ı��г��ֵ��ַ���Ӧ�Ĺ���������
	public static String[] array=new String[MAXN];
	
	//������ʱѹ���ļ���(��������Ʊ�����Ϣ)
	public static File tempfile=null;
	
	//Դ�ļ���
	public static String sourcefilename;
	//ѹ���ļ���
	public static String compressfilename;
	
	//��Ҫ��0��λ��
	public static int zero=0;
	
}
