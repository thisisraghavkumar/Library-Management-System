package filesystems;
import data.*;
import java.io.*;
import java.time.LocalDateTime;
public class Exchange {
	private String dir;
	public Exchange() {
		dir = "/F/JAVA Eclipse/LBMS/files/";
	}
	public boolean issue(String bid, String mid) {
		File b = new File(dir+"Books/"+bid);
		File m = new File(dir+"Members/"+mid);
		if(!b.exists()) {
			System.out.println("Book does not exists!");
			return false;
		}
		if(!m.exists()) {
			System.out.println("Member does not exists!" );
			return false;
		}
		Book tempBook = null;
		Member tempMem = null;
		ObjectInputStream bin = null;
	    ObjectInputStream min = null;
		try {
			 bin = new ObjectInputStream(new FileInputStream(b));
			 min = new ObjectInputStream(new FileInputStream(m));
			 tempBook = (Book)bin.readObject();
			 tempMem = (Member)min.readObject();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} catch (ClassNotFoundException e) {
			return false;
		} finally {
			if(bin!=null) {
				try {
					bin.close();
				} catch (IOException e) {
						return false;
				}
			}
			if(min!=null) {
				try {
					min.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
		if(tempBook == null) {
			System.out.println("Book object not created!");
			return false;
		}
		if(tempMem == null) {
			System.out.println("Member object not created!");
			return false;
		}
		if(!tempBook.isIssued()&&tempMem.getBooks().size()<5&&tempMem.getFineOutstanding()<=50) {
			tempBook.setIssued(true);
			tempBook.setMemId(tempMem.getMemId());
			tempBook.setDoi(LocalDateTime.now());
			tempMem.addBooks(bid);
			tempMem.setDoi(LocalDateTime.now());
		}else {
			if(tempBook.isIssued()) {
				System.out.println("Book already issued!");
				return false;
			}else if(tempMem.getBooks().size()>=5) {
				System.out.println("Member already has 5 books!");
				return false;
			}else {
				System.out.println("Member has outstanding fine of "+tempMem.getFineOutstanding()+"\n Member has to clear that first!");
				return false;
			}
		}
		ObjectOutputStream bout = null;
		ObjectOutputStream mout = null;
		try {
			bout = new ObjectOutputStream(new FileOutputStream(b));
		    mout = new ObjectOutputStream(new FileOutputStream(m));
			bout.writeObject(tempBook);
			mout.writeObject(tempMem);
		}catch(Exception e) {
			
		}finally {
			if(bout!=null) {
				try {
					bout.close();
				} catch (IOException e) {
					return false;
				}
			}
			if(mout!=null) {
				try {
					mout.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean returnBack(String bid,String mid) {
		File b = new File(dir+"Books/"+bid);
		File m = new File(dir+"Members/"+mid);
		if(!b.exists()) {
			System.out.println("Book does not exists!");
			return false;
		}
		if(!m.exists()) {
			System.out.println("Member does not exists!" );
			return false;
		}
		Book tempBook = null;
		Member tempMem = null;
		ObjectInputStream bin = null;
	    ObjectInputStream min = null;
		try {
			 bin = new ObjectInputStream(new FileInputStream(b));
			 min = new ObjectInputStream(new FileInputStream(m));
			 tempBook = (Book)bin.readObject();
			 tempMem = (Member)min.readObject();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} catch (ClassNotFoundException e) {
			return false;
		} finally {
			if(bin!=null) {
				try {
					bin.close();
				} catch (IOException e) {
						return false;
				}
			}
			if(min!=null) {
				try {
					min.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
		if(tempBook == null) {
			System.out.println("Book object not created!");
			return false;
		}
		if(tempMem == null) {
			System.out.println("Member object not created!");
			return false;
		}
		String memberid = tempBook.getMemId();
		if(!mid.equals(memberid)) {
			System.out.println("Member Ids does not match!");
			return false;
		}
		LocalDateTime today = LocalDateTime.now();
		int diff = today.getDayOfYear()-tempBook.getDoi().getDayOfYear();
		if(diff>15) {
			tempMem.setFineOutstanding(3*(diff-15));
			System.out.println("Member has to pay a fine of Rs"+3*(diff-15)+" for a delay of "+diff+" days. Fine added to outstanding.");
		}
		tempBook.setIssued(false);
		tempBook.setDoi(null);
		tempBook.setMemId(null);
		int index = tempMem.getBookIndex(bid);
		tempMem.removeBooks(bid);
		tempMem.removeDate(tempMem.getDoi().get(index));
		ObjectOutputStream bout = null;
		ObjectOutputStream mout = null;
		try {
			bout = new ObjectOutputStream(new FileOutputStream(b));
		    mout = new ObjectOutputStream(new FileOutputStream(m));
			bout.writeObject(tempBook);
			mout.writeObject(tempMem);
		}catch(Exception e) {
			
		}finally {
			if(bout!=null) {
				try {
					bout.close();
				} catch (IOException e) {
					return false;
				}
			}
			if(mout!=null) {
				try {
					mout.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean reissue(String bid, String mid) {
		boolean ret = returnBack(bid, mid);
		boolean issue = issue(bid, mid);
		if(ret&&issue) {
			System.out.println("Book reissued.");
			return true;
		}
		else {
			System.out.println("Error in reissue, try returning and issuing individdually.");
			return false;
		}
	}
}
