package huffman;
import java.io.File;
import java.io.IOException;
import getSourceFileMessage.*;
import tool.*;

public class Huffman {

	//获取_tool包的tools工具
	tool.Tools tools=null;
	//获取_tool包的function功能
	tool.Function function=null;
	
	//构造函数
	public Huffman() {
		super();
		tools=new tool.Tools();
		function=new Function();
	}
	
	//压缩文件
	@SuppressWarnings("static-access")
	public void SourceFileToHuffmanFile() {
		
		//获取目标文件信息并获取其哈夫曼树结构
		GetSourceFile source=new GetSourceFile();
		source.getSourceFile();
		//生成哈夫曼树
		function.createHuffmanTree();
		//获取每个节点的哈夫曼编码
		function.getHuffmanCode(function.root,function.root.code);
		//定义压缩文件名
		tools.compressfilename=tools.sourcefilename.replace(".txt", "(压缩文件).txt");
		//创建临时压缩文件(保存二进制编码信息)
				tools.tempfile=new File(tools.sourcefilename.replace(".txt", "(临时压缩文件).txt"));
		try {
			tools.tempfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//将二进制编码信息保存入临时压缩文件
		function.createTempFile();
		//文件压缩完成
		System.out.println("文件压缩完成！");
		
	}
	

}
