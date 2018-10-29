package tool;

/*
 * 定义哈夫曼树节点
 */

public class HufTreeNode {
	
	//构造函数
	public HufTreeNode() {
		super();
		// TODO Auto-generated constructor stub
		this.left=null;
		this.right=null;
		this.parent=null;
	}

	//节点保存的字符
	public char element;
	//该字符在文本中出现的频率
	public int rate;
	//该字符的哈夫曼编码
	public String code;
	//左孩子
	public HufTreeNode left;
	//右孩子
	public HufTreeNode right;
	//双亲节点
	public HufTreeNode parent;
	
}
		

