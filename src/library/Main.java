/**
 *
 * @Author: Shamal Rathnayaka
 * @SID: 11687499
 * @Subject: ITC515 - Professional Programming Practice
 * @Team: TeamCodeX
 */
package library;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import library.borrowbook.BorrowBookUI;
import library.borrowbook.BorrowBookControl;
import library.entities.Book;
import library.entities.Calendar;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;
import library.fixbook.FixBookUi;
import library.fixbook.FixBookControl;
import library.payfine.PayFineUI;
import library.payfine.PayFineControl;
import library.returnBook.ReturnBookUI;
import library.returnBook.rETURN_bOOK_cONTROL;

public class Main {
	
	private static Scanner in;
	private static Library library;
	private static String menu;
	private static Calendar calendar;
	private static SimpleDateFormat simpleDateFormat;

	private static String getMenu() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return stringBuilder.toString();
	}

	public static void main(String[] args) {		
		try {			
			in = new Scanner(System.in);
			library = Library.getInstance();
			calendar = Calendar.getInstance();
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
			for (Member member : library.listMembers()) {
				output(member);
			}
			output(" ");
			for (Book book : library.listBooks()) {
				output(book);
			}
						
			menu = getMenu();
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + simpleDateFormat.format(calendar.getDate()));
				String c = input(menu);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember();
					break;
					
				case "LM": 
					listMembers();
					break;
					
				case "B": 
					addBook();
					break;
					
				case "LB": 
					listBooks();
					break;
					
				case "FB": 
					fixBooks();
					break;
					
				case "L": 
					borrowBook();
					break;
					
				case "R": 
					returnBook();
					break;
					
				case "LL": 
					listCurrentLoans();
					break;
					
				case "P": 
					payFines();
					break;
					
				case "T": 
					incrementDate();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				Library.save();
			}			
		} catch (RuntimeException exception) {
			output(exception);
		}		
		output("\nEnded\n");
	}	

	private static void payFines() {
		new PayFineUI(new PayFineControl()).Run();
	}

	private static void listCurrentLoans() {
		output("");
		for (Loan loan : library.listCurrentLoans()) {
			output(loan + "\n");
		}		
	}

	private static void listBooks() {
		output("");
		for (Book book : library.listBooks()) {
			output(book + "\n");
		}		
	}

	private static void listMembers() {
		output("");
		for (Member member : library.listMembers()) {
			output(member + "\n");
		}		
	}

	private static void borrowBook() {
		new BorrowBookUI(new BorrowBookControl()).run();
	}

	private static void returnBook() {
		new ReturnBookUI(new rETURN_bOOK_cONTROL()).RuN();		
	}

	private static void fixBooks() {
		new FixBookUi(new FixBookControl()).RuN();		
	}

	private static void incrementDate() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			calendar.incrementDate(days);
			library.checkCurrentLoans();
			output(simpleDateFormat.format(calendar.getDate()));
			
		} catch (NumberFormatException exception) {
			 output("\nInvalid number of days\n");
		}
	}

	private static void addBook() {
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNumber = input("Enter call number: ");
		Book book = library.addBook(author, title, callNumber);
		output("\n" + book + "\n");
		
	}

	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String emailAddress = input("Enter email address: ");
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue();
			Member member = library.addMember(lastName, firstName, emailAddress, phoneNumber);
			output("\n" + member + "\n");
			
		} catch (NumberFormatException exception) {
			 output("\nInvalid phone number\n");
		}
	}

	private static String input(String prompt) {
		System.out.print(prompt);
		return in.nextLine();
	}
	
	private static void output(Object object) {
		System.out.println(object);
	}
}
