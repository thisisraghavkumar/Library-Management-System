package filesystems;
import data.*;
import java.io.*;
public class Search {
	private String dir;
	public Search() {
		dir = "/F/JAVA Eclipse/LBMS/files/";
	}
	public Book sBookId(String bid) {
		File f = new File(dir+"Books/"+bid);
		Book ret = null;
		if(!f.exists())
			System.out.println("Bid does not exists! ");
		else {
			ObjectInputStream s = null;
			try {
				s = new ObjectInputStream(new FileInputStream(f));
				ret = (Book)s.readObject();
			} catch (FileNotFoundException e) {
				return null;
			} catch (IOException e) {
				return null;
			} catch (ClassNotFoundException e) {
				return null;
			}finally {
				if(s!=null)
					try {
						s.close();
					} catch (IOException e) {
						return null;
					}
			}
			
		}
		return ret;
	}
	public Member sMemId(String bid) {
		File f = new File(dir+"Members/"+bid);
		Member ret = null;
		if(!f.exists())
			System.out.println("MemID does not exists! ");
		else {
			ObjectInputStream s = null;
			try {
				s = new ObjectInputStream(new FileInputStream(f));
				ret = (Member)s.readObject();
			} catch (FileNotFoundException e) {
				return null;
			} catch (IOException e) {
				return null;
			} catch (ClassNotFoundException e) {
				return null;
			}finally {
				if(s!=null)
					try {
						s.close();
					} catch (IOException e) {
						return null;
					}
			}
			
		}
		return ret;
	}
	public void sAuthor(String[] name) {
		System.out.println("Searching author...");
		File parent = new File(dir+"Books");
		if(!parent.exists())
			System.out.println("Dir book not found!");
		File[] books = parent.listFiles();
		System.out.println(books.length);
		ObjectInputStream[] objBook = new ObjectInputStream[books.length];
		if(books.length==0)
			System.out.println("No books found in dir "+parent.getAbsolutePath());
		try {
			//System.out.println("Inside try");
		for(int i=0;i<books.length;i++){
				//System.out.println("inside for");
				objBook[i] = new ObjectInputStream(new FileInputStream(books[i]));
				Book b = (Book)objBook[i].readObject();
				//b.printList();
				int ctr = 0;
				//System.out.println("analysing "+b.getBid());
				if(name.length>b.getAuthor().size()){
					//System.out.println("Sizer bada hai");
					continue;
				}else {
					for(int j=0;j<name.length;j++) {
						for(int k=0;k<b.getAuthor().size();k++) {
							if(name[j].equals(b.getAuthor().get(k)))
								ctr++;
						}
					}
					if(ctr>0)
						b.printList();
					else
						continue;
				}
			}
		}catch (IOException e) {
				return;
		} catch (ClassNotFoundException e) {
				return;
		} finally {
			try {
			for(int i=0;i<books.length;i++) {
				if(objBook[i]!=null)
					objBook[i].close();
			}
			}catch(Exception e) {
				return;
			}
		}
		
	}
	public void sKeywords(String[] name) {
		File parent = new File(dir+"Books");
		File[] books = parent.listFiles();
		ObjectInputStream[] objBook = new ObjectInputStream[books.length];
		try {
		for(int i=0;i<books.length;i++){
				objBook[i] = new ObjectInputStream(new FileInputStream(books[i]));
				Book b = (Book)objBook[i].readObject();
				int ctr = 0;
				if(name.length>b.getKeywords().size()){
					continue;
				}else {
					for(int j=0;j<name.length;j++) {
						for(int k=0;k<b.getKeywords().size();k++) {
							if(name[j].equals(b.getKeywords().get(k)))
								ctr++;
						}
					}
					if(ctr==name.length)
						b.printList();
					else
						continue;
				}
			}
		}catch (IOException e) {
				return;
		} catch (ClassNotFoundException e) {
				return;
		} finally {
			try {
			for(int i=0;i<books.length;i++) {
				if(objBook[i]!=null)
					objBook[i].close();
			}
			}catch(Exception e) {
				return;
			}
		}
		
	}
	public void sFineOut(int n) {
		File m = new File(dir+"Members");
		if(!m.exists())
			System.out.println("Cannot open dir Members!");
		File[] members = m.listFiles();
		Member mobj = null;
		ObjectInputStream mem = null;
		try {
			for(int i=0;i<members.length;i++) {
				mem = new ObjectInputStream(new FileInputStream(members[i]));
				mobj = (Member)mem.readObject();
				if(mobj.getFineOutstanding()>=n)
					mobj.printList();
				mem.close();
			}
		}catch(Exception e) {
			return;
		}
	}
	public void sBookName(String name) {
		File b = new File(dir+"Books");
		if(!b.exists())
			System.out.println("Dir Books not found!");
		File[] books = b.listFiles();
		ObjectInputStream bookF = null;
		Book bObj;
		try {
			for(int i=0;i<books.length;i++) {
				bookF = new ObjectInputStream(new FileInputStream(books[i]));
				bObj = (Book)bookF.readObject();
				if(bObj.getBname().equals(name)) {
					bObj.printList();
				}
				bookF.close();
			}
		}catch(Exception e) {
			return;
		}
	}
	public void sMemName(String name) {
		File b = new File(dir+"Members");
		if(!b.exists())
			System.out.println("Dir Members not found!");
		File[] books = b.listFiles();
		ObjectInputStream bookF = null;
		Member bObj;
		try {
			for(int i=0;i<books.length;i++) {
				bookF = new ObjectInputStream(new FileInputStream(books[i]));
				bObj = (Member)bookF.readObject();
				if(bObj.getMname().equals(name)) {
					bObj.printList();
				}
				bookF.close();
			}
		}catch(Exception e) {
			return;
		}
	}

}
