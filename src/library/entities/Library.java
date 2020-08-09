/**
 *
 * @Author: Shamal Rathnayaka
 * @SID: 11687499
 * @Subject: ITC515 - Professional Programming Practice
 * @Team: TeamCodeX
 */
package library.entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {
	
	private static final String LIBRARY_FILE = "library.obj";
	private static final int LOAN_LIMIT = 2;
	private static final int LOAN_PERIOD = 2;
	private static final double FINE_PER_DAY = 1.0;
	private static final double MAX_FINES_OWED = 1.0;
	private static final double DAMAGE_FEE = 2.0;
	private static Library LIBRARY;

	private int bookId;
	private int memberId;
	private int loanId;

	private Date loanDate;
	private Map<Integer, Book> catalog;
	private Map<Integer, Member> members;
	private Map<Integer, Loan> loans;
	private Map<Integer, Loan> currentLoans;
	private Map<Integer, Book> damagedBooks;
	

	private Library() {
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		bookId = 1;
		memberId = 1;
		loanId = 1;
	}

	
	public static synchronized Library getInstance() {
		if (LIBRARY == null) {
			Path PATH = Paths.get(LIBRARY_FILE);
			if (Files.exists(PATH)) {	
				try (ObjectInputStream LiBrArY_FiLe = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {
			    
					LIBRARY = (Library) LiBrArY_FiLe.readObject();
					Calendar.gEtInStAnCe().SeT_DaTe(LIBRARY.loanDate);
					LiBrArY_FiLe.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else LIBRARY = new Library();
		}
		return LIBRARY;
	}

	
	public static synchronized void save() {
		if (LIBRARY != null) {
			LIBRARY.loanDate = Calendar.gEtInStAnCe().gEt_DaTe();
			try (ObjectOutputStream LiBrArY_fIlE = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {
				LiBrArY_fIlE.writeObject(LIBRARY);
				LiBrArY_fIlE.flush();
				LiBrArY_fIlE.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int getBookId() {
		return bookId;
	}
	
	
	public int getMemberId() {
		return memberId;
	}
	
	
	private int getNextBookId() {
		return bookId++;
	}

	
	private int getNextMemberId() {
		return memberId++;
	}

	
	private int getNextLoanId() {
		return loanId++;
	}

	
	public List<Member> listMembers() {
		return new ArrayList<Member>(members.values());
	}


	public List<Book> listBooks() {
		return new ArrayList<Book>(catalog.values());
	}


	public List<Loan> listCurrentLoans() {
		return new ArrayList<Loan>(currentLoans.values());
	}


	public Member addMember(String lastName, String firstName, String email, int phoneNo) {
		Member member = new Member(lastName, firstName, email, phoneNo, getNextMemberId());
		members.put(member.GeT_ID(), member);
		return member;
	}

	
	public Book addBook(String a, String t, String c) {
		Book b = new Book(a, t, c, getNextBookId());
		catalog.put(b.gEtId(), b);
		return b;
	}

	
	public Member getMember(int memberId) {
		if (members.containsKey(memberId))
			return members.get(memberId);
		return null;
	}

	
	public Book getBook(int bookId) {
		if (catalog.containsKey(bookId))
			return catalog.get(bookId);
		return null;
	}

	
	public int getLoanLimit() {
		return LOAN_LIMIT;
	}

	
	public boolean isMemberBorrow(Member member) {
		if (member.gEt_nUmBeR_Of_CuRrEnT_LoAnS() == LOAN_LIMIT)
			return false;
				
		if (member.FiNeS_OwEd() >= MAX_FINES_OWED)
			return false;
				
		for (Loan loan : member.GeT_LoAnS()) 
			if (loan.Is_OvEr_DuE()) 
				return false;
			
		return true;
	}

	
	public int getNumberOfLoansRemainingForMember(Member member) {
		return LOAN_LIMIT - member.gEt_nUmBeR_Of_CuRrEnT_LoAnS();
	}

	
	public Loan issueLoan(Book book, Member member) {
		Date dueDate = Calendar.gEtInStAnCe().gEt_DuE_DaTe(LOAN_PERIOD);
		Loan loan = new Loan(getNextLoanId(), book, member, dueDate);
		member.TaKe_OuT_LoAn(loan);
		book.BoRrOw();
		loans.put(loan.GeT_Id(), loan);
		currentLoans.put(book.gEtId(), loan);
		return loan;
	}
	
	
	public Loan getLoanByBookId(int bookId) {
		if (currentLoans.containsKey(bookId))
			return currentLoans.get(bookId);
		
		return null;
	}

	
	public double calculateOverDueFine(Loan loan) {
		if (loan.Is_OvEr_DuE()) {
			long DaYs_OvEr_DuE = Calendar.gEtInStAnCe().GeT_DaYs_DiFfErEnCe(loan.GeT_DuE_DaTe());
			double fInE = DaYs_OvEr_DuE * FINE_PER_DAY;
			return fInE;
		}
		return 0.0;		
	}


	public void dischargeLoan(Loan cUrReNt_LoAn, boolean iS_dAmAgEd) {
		Member mEmBeR = cUrReNt_LoAn.GeT_MeMbEr();
		Book bOoK  = cUrReNt_LoAn.GeT_BoOk();
		
		double oVeR_DuE_FiNe = calculateOverDueFine(cUrReNt_LoAn);
		mEmBeR.AdD_FiNe(oVeR_DuE_FiNe);	
		
		mEmBeR.dIsChArGeLoAn(cUrReNt_LoAn);
		bOoK.ReTuRn(iS_dAmAgEd);
		if (iS_dAmAgEd) {
			mEmBeR.AdD_FiNe(DAMAGE_FEE);
			damagedBooks.put(bOoK.gEtId(), bOoK);
		}
		cUrReNt_LoAn.DiScHaRgE();
		currentLoans.remove(bOoK.gEtId());
	}


	public void checkCurrentLoans() {
		for (Loan lOaN : currentLoans.values())
			lOaN.cHeCk_OvEr_DuE();
				
	}


	public void repairBook(Book cUrReNt_BoOk) {
		if (damagedBooks.containsKey(cUrReNt_BoOk.gEtId())) {
			cUrReNt_BoOk.RePaIr();
			damagedBooks.remove(cUrReNt_BoOk.gEtId());
		}
		else 
			throw new RuntimeException("Library: repairBook: book is not damaged");
		
		
	}
	
	
}
