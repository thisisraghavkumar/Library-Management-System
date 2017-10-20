package filesystems;
import java.io.*;

import data.Book;
public class Sketchpad {


	public static void main(String[] args) throws Exception {
		String dir = "/F/JAVA Eclipse/LBMS/files/";
		File bk = new File(dir+"Books/"+"db031");
		//File me = new File(dir+"Members");
		if(!bk.exists()) {
			System.out.println("Dir Books does not exist.");
			return;
		}
		//if(!me.exists()) {
			//System.out.println("Dir Members does not exist.");
			//return;
		//}
		RandomAccessFile bkr = new RandomAccessFile(bk,"rws");
		//RandomAccessFile mem = new RandomAccessFile(me, "rwd");
		ObjectInputStream bin =new ObjectInputStream(new FileInputStream(bk));
		ObjectOutputStream bout = new ObjectOutputStream(new FileOutputStream(bk));
		byte[] barray = new byte[100];
		int s = bkr.read(barray);
		while(s!=-1) {
			System.out.println((char)s);
			s = bkr.read();
		}
//		System.out.println(bkr.readChar());
//		System.out.println(bkr.read());
//		System.out.println(bkr.read());
//		System.out.println(bkr.read());
//		System.out.println(bkr.read());
		bkr.close();
		bin.close();
		bout.close();
	}

}
