package filesystems;
import java.io.*;
public class Delete {
	private String dir;
	public Delete() {
		dir = "/F/JAVA Eclipse/LBMS/files/";
	}
	
	public boolean book(String bid) {
		File f = new File(dir+"Books/"+bid);
		if(f.exists()) {
			f.delete();
			return true;
		}
		System.out.println("File does not exist!");
		return false;
	}
	
	public boolean member(String mid) {
		File f = new File(dir+"Members/"+mid);
		if(f.exists()) {
			f.delete();
			return true;
		}
		System.out.println("File does not exist!");
		return false;
	}
}
