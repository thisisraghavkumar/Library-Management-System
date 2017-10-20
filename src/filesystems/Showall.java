package filesystems;
import data.*;
import java.io.*;

public class Showall {
	private String dir;
	public Showall(){
		dir = "/F/JAVA Eclipse/LBMS/files/";
	}
	public void books() {
		File parent = new File(dir+"Books");
		File[] ledgers = parent.listFiles();
//		for(int i=0;i<ledgers.length;i++) {
//			System.out.println(ledgers[i].getName());
//		}
		ObjectInputStream book = null;
		Book b = null;
		for(int i = 0;i<ledgers.length;i++) {
			try {
				book = new ObjectInputStream(new FileInputStream(ledgers[i]));
				System.out.println(book.available());
				b = (Book)book.readObject();
				b.printList();
				
			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
				return;
			} catch (IOException e) {
				System.out.println("Error in reading");
				return;			} catch (ClassNotFoundException e) {
					System.out.println("No class!");
				return;
			} finally {
				try {
					book.close();
				} catch (IOException e) {
					return;
				}
			}
			
		}
	}
	public void members() {
		File parent = new File(dir+"Members");
		File[] ledgers = parent.listFiles();
		ObjectInputStream mem = null;
		Member b = null;
		for(int i = 0;i<ledgers.length;i++) {
			try {
				mem= new ObjectInputStream(new FileInputStream(ledgers[i]));
				b = (Member)mem.readObject();
				b.printList();
				System.out.println();
				System.out.println();
			} catch (FileNotFoundException e) {
				return;
			} catch (IOException e) {
				return;			} catch (ClassNotFoundException e) {
				return;
			} finally {
				try {
					mem.close();
				} catch (IOException e) {
					return;
				}
			}
			
		}
	}
}
