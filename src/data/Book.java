package data;
import java.io.Serializable;
import java.util.ArrayList;

import java.time.LocalDateTime;
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final private String bid;
	final private String bname;
	final private ArrayList<String> author;
	private boolean issued;
	private String memId;
	private LocalDateTime doi;
	final private String price;
	private String add; 
	private ArrayList<String> keywords;
	public Book(String bid, String bname, ArrayList<String> Authors, String price, ArrayList<String> keywords, String add) {
		this.bid = bid.substring(0);
		this.bname = bname.substring(0);
		this.author = new ArrayList<String>();
		this.keywords = new ArrayList<String>();
		for(int i=0;i<Authors.size();i++) {
			this.author.add(Authors.get(i));
		}
		for(String word:keywords) {
			this.keywords.add(word);
		}
		this.price = price;
		this.setAdd(add);
	}
	public boolean isIssued() {
		return  issued;
	}
	public void setIssued(boolean issued) {
		this.issued = issued;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public LocalDateTime getDoi() {
		return  doi;
	}
	public void setDoi(LocalDateTime doi) {
		this.doi = doi;
	}
	public String getBid() {
		return  bid;
	}
	public String getBname() {
		return  bname;
	}
	public String getPrice() {
		return price;
	}
	public void printList() {
		System.out.print(bid+"      "+bname+"         ");
		for(int i=0;i<author.size()-1;i++) {
			System.out.print(author.get(i)+",");
		}
		System.out.print(author.get(author.size()-1)+"     ");
		
		String is;
		if(issued)
			is = "yes";
		else
			is = "no";
		System.out.print("Issued: "+is+"    ");
		if(issued) {
			System.out.print(" to "+memId+" on "+doi.toLocalDate().toString());
		}else {
			System.out.println("Available at "+add);
		}
		System.out.println();
	}
	public void print() {
		System.out.println("Book Id : "+bid);
		System.out.println("Title : "+bname);
		System.out.print("Author(s) :");
		int i=0;
		while(i<author.size()-1) {
			System.out.print(author.get(i)+",");
			i++;
		}
		System.out.println(author.get(i));
		System.out.println("Keywords : ");
		i=0;
		while(i<keywords.size()-1) {
			System.out.print(keywords.get(i)+",");
			i++;
		}
		System.out.println(keywords.get(i));
		System.out.println("Price : Rs"+price);
		if(issued) {
			System.out.println("Issued to member "+memId+" on "+doi.toLocalDate().toString());
		}else {
			System.out.println("Available at "+add);
		}
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public ArrayList<String> getAuthor() {
		return this.author;
	}
	public ArrayList<String> getKeywords(){
		return this.keywords;
	}
}
