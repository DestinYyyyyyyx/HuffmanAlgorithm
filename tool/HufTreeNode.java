package tool;

/*
 * ������������ڵ�
 */

public class HufTreeNode {
	
	//���캯��
	public HufTreeNode() {
		super();
		// TODO Auto-generated constructor stub
		this.left=null;
		this.right=null;
		this.parent=null;
	}

	//�ڵ㱣����ַ�
	public char element;
	//���ַ����ı��г��ֵ�Ƶ��
	public int rate;
	//���ַ��Ĺ���������
	public String code;
	//����
	public HufTreeNode left;
	//�Һ���
	public HufTreeNode right;
	//˫�׽ڵ�
	public HufTreeNode parent;
	
}
		

