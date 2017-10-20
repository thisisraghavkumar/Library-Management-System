package filesystems;
import java.io.*;
import data.*;

public class Create {
	private String dir;
	public Create() {
		dir = "/F/JAVA Eclipse/LBMS/files/";
	}
	public boolean book(Book b) {
		//System.out.println("New file: "+dir+"Books/"+b.getBid());
		//System.out.println();
		File f = new File(dir+"Books/"+b.getBid());
		try {
			f.createNewFile();
		} catch (IOException e1) {
			return false;
		}
		try(ObjectOutputStream fl = new ObjectOutputStream(new FileOutputStream(f));){
			
			
			fl.writeObject(b);
			fl.flush();
			}
		catch(Exception e) { 
			return false;
		}
		return true;
	}
	public boolean member(Member m) {
		File fil = new File(dir+"Members/"+m.getMemId());
		try {
			fil.createNewFile();
		} catch (IOException e) {
			return false;
		}
		try(ObjectOutputStream file =new ObjectOutputStream(new FileOutputStream(fil))){
			file.writeObject(m);
			file.flush();
		}catch(Exception e) {
			return false;
		}
		return true;
	}
}
		


