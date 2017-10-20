package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Member implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		final private String memId;
		final private String mname;
		private String contactNo;
		private ArrayList<String> books;
		private ArrayList<LocalDateTime> doi;
		private int fineOutstanding;
		private int finePaid;
		private String address;
		public Member(String id, String name, String cno, String add) {
			books = new ArrayList<String>();
			doi = new ArrayList<LocalDateTime>();
			this.memId=id;
			this.mname=name;
			this.setContactNo(cno);
			this.setAddress(add);
			this.fineOutstanding=0;
			this.finePaid=0;
		}
		public String getContactNo() {
			return  contactNo;
		}
		public void setContactNo(String contactNo) {
			this.contactNo = contactNo;
		}
		public ArrayList<String> getBooks() {
			return  books;
		}
		public int getBookIndex(String bid) {
			return this.books.indexOf(bid);
		}
		public void removeBooks(String bid) {
			this.books.remove(bid);
		}
		public void addBooks(String bid) {
			this.books.add(bid);
		}
		public void removeDate(LocalDateTime dt) {
			this.doi.remove(dt);
		}
		public ArrayList<LocalDateTime> getDoi() {
			return  doi;
		}
		public void setDoi(LocalDateTime doi) {
			this.doi.add(doi);
		}
		public int getFineOutstanding() {
			return  fineOutstanding;
		}
		public void setFineOutstanding(int fineOutstanding) {
			this.fineOutstanding += fineOutstanding;
		}
		public int getFinePaid() {
			return finePaid;
		}
		public void setFinePaid(int finePaid) {
			this.finePaid += finePaid;
		}
		public String getAddress() {
			return  address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getMemId() {
			return  memId;
		}
		public String getMname() {
			return  mname;
		}
		public void printList() {
			System.out.println(memId+"   "+mname+"   "+contactNo+" Number_of_books:"+books.size()+"    Fine outstanding:Rs"+fineOutstanding+" Fine Paid:Rs"+finePaid);
		}
		public void print() {
			System.out.println("Member Id : "+memId);
			System.out.println("Name : "+mname);
			System.out.println("Contact : "+contactNo);
			System.out.println("Number of books issued : "+books.size());
			if(!(books.size()==0)&&!(doi.size()==0)&&books.size()==doi.size()) {
				System.out.println("Book Id------------------------Date Of Issue");
				for(int i=0;i<books.size();i++) {
					System.out.println(books.get(i)+"------------------------"+doi.get(i).toLocalDate().toString());
				}
			}else {
				System.out.println("Member currently has no books issued!");
			}
			if(memId.charAt(4)=='U'||memId.charAt(4)=='M'||memId.charAt(4)=='P')
				System.out.println("Address : "+address);
			else
				System.out.println("Department : "+address);
			System.out.println("Fine Outstanding : Rs"+fineOutstanding);
			System.out.println("Fine Paid : Rs"+finePaid);
		}
}
