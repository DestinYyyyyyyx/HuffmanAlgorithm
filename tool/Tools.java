package tool;

import java.io.File;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

/*
 * 程序用到的各种数据结构及变量
 */
public class Tools {

	//array数组大小
	private static int MAXN=1000000;
	
	//构造函数
	public Tools() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//优先队列用于保存要压缩的文档的哈夫曼树结构
	public static PriorityQueue<HufTreeNode> queue=new PriorityQueue<HufTreeNode>(
			new Comparator<HufTreeNode>() {
				public int compare(HufTreeNode node1,HufTreeNode node2) {
						      return node1.rate-node2.rate;
				}
			});
	
	//用TreeMap记录字符出现情况
	//TODO优化 : 将map中的元素按value小的排序
	@SuppressWarnings("rawtypes")
	public static TreeMap map=new TreeMap<String,String>();
	
	//array记录文本中出现的字符对应的哈夫曼编码
	public static String[] array=new String[MAXN];
	
	//定义临时压缩文件名(保存二进制编码信息)
	public static File tempfile=null;
	
	//源文件名
	public static String sourcefilename;
	//压缩文件名
	public static String compressfilename;
	
	//需要补0的位数
	public static int zero=0;
	
}
