/**
 *
 * @Author: Shamal Rathnayaka
 * @SID: 11687499
 * @Subject: ITC515 - Professional Programming Practice
 * @Team: TeamCodeX
 */
package library.borrowbook;
import java.util.ArrayList;
import java.util.List;

import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;

public class BorrowBookControl {
	
	private BorrowBookUI ui;
	
	private Library library;
	private Member member;
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private ControlState state;
	
	private List<Book> pendingList;
	private List<Loan> completedList;
	private Book book;
	
	
	public BorrowBookControl() {
		this.library = Library.getInstance();
		state = ControlState.INITIALISED;
	}
	

	public void setUi(BorrowBookUI ui) {
		if (!state.equals(ControlState.INITIALISED))
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.ui = ui;
		ui.SeT_StAtE(BorrowBookUI.UIState.READY);
		state = ControlState.READY;
	}

		
	public void swiped(int memberId) {
		if (!state.equals(ControlState.READY))
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId);
		if (member == null) {
			ui.DiSpLaY("Invalid memberId");
			return;
		}
		if (library.isMemberBorrow(member)) {
			pendingList = new ArrayList<>();
			ui.SeT_StAtE(BorrowBookUI.UIState.SCANNING);
			state = ControlState.SCANNING;
		}
		else {
			ui.DiSpLaY("Member cannot borrow at this time");
			ui.SeT_StAtE(BorrowBookUI.UIState.RESTRICTED);
		}
	}
	
	
	public void scanned(int bookId) {
		book = null;
		if (!state.equals(ControlState.SCANNING))
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
			
		book = library.getBook(bookId);
		if (book == null) {
			ui.DiSpLaY("Invalid bookId");
			return;
		}
		if (!book.is_Available()) {
			ui.DiSpLaY("Book cannot be borrowed");
			return;
		}
		pendingList.add(book);
		for (Book book : pendingList)
			ui.DiSpLaY(book.toString());
		
		if (library.getNumberOfLoansRemainingForMember(member) - pendingList.size() == 0) {
			ui.DiSpLaY("Loan limit reached");
			complete();
		}
	}
	
	
	public void complete() {
		if (pendingList.size() == 0)
			cancel();
		
		else {
			ui.DiSpLaY("\nFinal Borrowing List");
			for (Book book : pendingList)
				ui.DiSpLaY(book.toString());
			
			completedList = new ArrayList<Loan>();
			ui.SeT_StAtE(BorrowBookUI.UIState.FINALISING);
			state = ControlState.FINALISING;
		}
	}


	public void commitLoans() {
		if (!state.equals(ControlState.FINALISING))
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
			
		for (Book book : pendingList) {
			Loan loan = library.issueLoan(book, member);
			completedList.add(loan);
		}
		ui.DiSpLaY("Completed Loan Slip");
		for (Loan loan : completedList)
			ui.DiSpLaY(loan.toString());
		
		ui.SeT_StAtE(BorrowBookUI.UIState.COMPLETED);
		state = ControlState.COMPLETED;
	}

	
	public void cancel() {
		ui.SeT_StAtE(BorrowBookUI.UIState.CANCELLED);
		state = ControlState.CANCELLED;
	}
	
	
}
