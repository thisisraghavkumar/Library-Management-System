package terminal;
import data.*;
import filesystems.*;
import java.io.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;
public class Library {
	private static<T> boolean contains(T[] arr, T inp) {
		for(int i=0;i<arr.length;i++)
			if(arr[i].equals(inp))
				return true;
		return false;
	}
	private static String trim(String str) {
		
		if(str.charAt(0)=='"'&&str.charAt(str.length()-1)=='"') {
			
			return str.substring(1,str.length()-1);
		}
		return str;
	}
	private static String[] parse(String input) {
		boolean inQuote = false;
		ArrayList<Integer> spaces = new ArrayList<Integer>();
		
		for(int i= 0;i<input.length();i++) {
			if(input.charAt(i)=='"')
				inQuote = !inQuote;
			if(input.charAt(i)==' '||input.charAt(i)==',') {
				if(!inQuote)
				spaces.add(i);
			}
		}
		String[] words = new String[spaces.size()+1];
		int initialIndex=0;
		int i;
		for(i=0;i<spaces.size();i++) {
			words[i] = trim(input.substring(initialIndex, spaces.get(i)));
			initialIndex = spaces.get(i)+1;
		}
		words[i] = trim(input.substring(initialIndex,input.length()));
		return words;
	}
	public static void terminal() {
		Scanner in = new Scanner(System.in);
		String[] commands;
		String dir = "/F/JAVA Eclipse/LBMS/files/";
		File books = new File(dir+"Books");
		File members = new File(dir+"Members");
		if(!books.exists()) {
			System.out.println("Cannot open dir Books!");
			in.close();
			return;
		}
		if(!members.exists()) {
			System.out.println("Cannot open dir Members!");
			in.close();
			return;
		}
		String instruction;
		LocalDateTime sysTime;
		Exchange exchange = new Exchange();
		Search search = new Search();
		Create create = new Create();
		Delete delete = new Delete();
		Showall show = new Showall();
		
		do {
			sysTime = LocalDateTime.now();
			System.out.print(sysTime.toString()+">>>");
			instruction = in.nextLine();
			commands = parse(instruction);
			if(commands[0].equals("logoff"))
				continue;
			if(commands[0].equals("add")) {
				if(commands.length<2) {
					
					System.out.println("Insufficient Parameters");
					System.out.println("Usage: add book or add member");
					continue;
				}
				if(commands[1].equals("book")) {
					String name;
					String id;
					ArrayList<String> auth = new ArrayList<String>();
					ArrayList<String> key = new ArrayList<String>();
					String loc;
					System.out.print("Book Id : ");
					id = in.nextLine();
					if(contains(books.list(),id)) {
						System.out.println("Book Id already exists!");
						continue;
					}
					System.out.print("Title : ");
					name = in.nextLine();
					System.out.print("Authors: To finish writing author enter -q in new line ");
					System.out.print("Author : ");
					String a = in.nextLine();
					while(!a.equals("-q")) {
						auth.add(a);
						System.out.print("Author : " );
						a=in.nextLine();
						
					}
					System.out.print("Keywords: To finish writing author enter -q in new line ");
					System.out.print("Keyword : ");
					a = in.nextLine();
					while(!a.equals("-q")) {
						key.add(a);
						System.out.print("Keyword : " );
						a=in.nextLine();
						
					}
					System.out.print("Location : ");
					loc = in.nextLine();
					System.out.print("Price : ");
					String p = in.nextLine();
					
					boolean success=create.book(new Book(id,name,auth,p,key,loc));
					if(success) {
						System.out.println("Book created!");
					}else
						System.out.println("Try again!");
				}else
				if(commands[1].equals("member")) {
					System.out.print("Member Id :");
					String mid = in.nextLine();
					if(contains(members.list(),mid)) {
						System.out.println("Member Id already exists!");
						continue;
					}
					System.out.print("Member Name : ");
					String mname = in.nextLine();
					System.out.print("Contact Nubmer :");
					String cno = in.nextLine();
					if(mid.charAt(4)=='U'||mid.charAt(4)=='M'||mid.charAt(4)=='P')
						System.out.print("Address : ");
					else
						System.out.print("Department : ");
					String ad = in.nextLine();
					boolean success=create.member(new Member(mid,mname,cno,ad));
					if(success)
						System.out.println("Member created!");
					else
						System.out.println("Try again!");
					
				}
			}else
			if(commands[0].equals("delete")) {
				if(commands.length<3) {
					System.out.println("Insufficient parameters!");
					System.out.println("Usage: delete asset assetId");
					System.out.println("asset = book,member");
					System.out.println("assetId = bookId,memberId");
					continue;
				}
				if(commands[1].equals("book")) {
					boolean suc = delete.book(commands[2]);
					if(suc) {
						System.out.println("Book successfully deleted!");
						
					}else
						System.out.println("Try again!");
				}else
				if(commands[1].equals("member")) {
					boolean suc = delete.member(commands[2]);
					if(suc) {
						System.out.println("Member successfully deleted!");
						
					}else
						System.out.println("Try again!");
				}
			}else
			if(commands[0].equals("issue")) {
				if(commands.length<3) {
					System.out.println("Insufficient parameters!");
					System.out.println("Usage: issue bookId memberId");
					continue;
				}
				boolean success=exchange.issue(commands[1],commands[2]);
				if(success)
					System.out.println("Books issued!");
				else
					System.out.println("Try again!");
			}else
			if(commands[0].equals("return")) {
				if(commands.length<3) {
					System.out.println("Insufficient parameters!");
					System.out.println("Usage: return bookId memberId");
					continue;
				}
				boolean success=exchange.returnBack(commands[1], commands[2]);
				if(success==true)
					System.out.println("Book returned!");
				else
					System.out.println("Try again!");
			}else
			if(commands[0].equals("reissue")) {
				if(commands.length<3) {
					System.out.println("Insufficient parameters!");
					System.out.println("Usage: reissue bookId memberId");
					continue;
				}
				boolean success = exchange.reissue(commands[1],commands[2]);
				if(success)
					System.out.println("Book reissued!");
				else
					System.out.println("Try again!");
			}else
			if(commands[0].equals("search")) {
				if(commands.length<3) {
					System.out.println("Insuffiecient parameters");
					System.out.println("Usage: search option term(s)");
					System.out.println("option = book,member,author,keyword");
					System.out.println("term(s) = bookId,memberId,space seperated list of author name(s),space seperated list of keyword(s)");
					continue;
				}
				if(commands[1].equals("book")) {
					if(commands[2].equals("id")) {
					Book found=search.sBookId(commands[3]);
					if(found!=null)
						found.print();
					else
						System.out.println("BookId not found!");
					}else
					if(commands[2].equals("name")) {
						search.sBookName(commands[3]);
					}
				}else
				if(commands[1].equals("member")) {
					if(commands[2].equals("id")) {
						Member found=search.sMemId(commands[3]);
						if(found!=null)
							found.print();
						else
							System.out.println("MemberId not found!");
					}else
					if(commands[2].equals("name")) {
						search.sMemName(commands[3]);
					}
				}else {
					String[] list = new String[commands.length-2];
					for(int i=0;i<list.length;i++) {
						list[i] = commands[2+i];
					}
					if(commands[1].equals("author")) {
						search.sAuthor(list);
					}else
					if(commands[1].equals("keyword")) {
						search.sKeywords(list);
					}else {
						System.out.println("Incorrect parameters");
						System.out.println("Usage: search option term(s)");
						System.out.println("option = book id/name,member id/name,author,keyword");
						System.out.println("term(s) = bookId,bookName,memberId,memberName,space seperated list of author name(s),space seperated list of keyword(s)");
						continue;
					}
				}
			}else
			if(commands[0].equals("help")) {
				System.out.println("This LBMS has been developed on jdk1.8. It supports the following functions, their usage is listed alongside.");
				System.out.println("This system works in a bash like manner, thus all the function name and parameters must be seperated by space.");
				System.out.println("(1) Add      - books            : add book");
				System.out.println("             - members          : add member");
				System.out.println("(2) Delete   - book             : delete book bookI d");
				System.out.println("             - member           : delete member memId");
				System.out.println("(3) Exchange - issue            : issue bookId memberId");
				System.out.println("             - return           : return bookId memberId");
				System.out.println("             - reissue          : reissue bookId memberId");
				System.out.println("(4) Search   - bookId           : search book id bookId");
				System.out.println("             - memberId         : search member id memberId");
				System.out.println("             - bookName         : search book name bookName");
				System.out.println("             - memberName       : search member name memberName");
				System.out.println("             - author(s)        : search author [space seperated list of author names]");
				System.out.println("             - keyword(s)       : search keyword [space seperated list of keywords");
				System.out.println("(5) Showall  - books            : showall books");
				System.out.println("             - members          : showall members");
			}else
			if(commands[0].equals("info")) {
				System.out.println("Developed on : Java SE 8");
				System.out.println("Developed by : Raghav Kumar");
				System.out.println("               2016UCO1640");
				System.out.println("Developed at : Netaji Subhash Institue Of Technology, Delhi University");
			}else
			if(commands[0].equals("showall")) {
				if(commands.length<2) {
					System.out.println("Insufficient Parameters!");
					System.out.println("Usage: showall books/members");
					continue;
				}
				if(commands[1].equals("books")) {
					show.books();
				}else
				if(commands[1].equals("members")) {
					show.members();
				}
			}
		}while(!commands[0].equals("logoff"));
		System.out.println("Logging off...");
		in.close();
	}
	public static void main(String[] args) {
		System.out.println("Welcome to LBMS, for help enter help, for information about program enter info, to quit enter logoff.");
		terminal();
	}
}
