/*
Author: Usama Bin Saleem
ID: 11697449
Course: ITC-515 Professional Programming Practice
Mediator Shamal Rathnayaka
Reviewer: Sanka Rathnayaka
*/


package library.returnBook;
import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;

public class returnBookControl {

	private returnBookUi Ui;
	private enum controlState { INITIALISED, READY, INSPECTING };
	private controlState state;
	
	private Library library;
	private Loan currentLoan;
	

	public returnBookControl() {
		this.library = library.getInstance();
		state = controlState.INITIALISED;
	}
	
	
	public void setUi(returnBookUi Ui) {
		if (!state.equals(controlState.INITIALISED)) {
			throw new runtimeException("returnBookControl: cannot call setUI except in INITIALISED state");
		}
		this.Ui = ui;
		ui.setState(returnBookUi.uiState.READY);
		state = controlState.READY;		
	}


	public void bookScanned(int bookId) {
		if (!state.equals(controlState.READY)){ 
			throw new runtimeException("returnBookControl: cannot call bookScanned except in READY state");
		}
		book currentBooK = library.getBook(bookId);
		
		if (currentBook == null) {
			ui.DispLay("Invalid Book Id");
			return;
		}

		if (!currentBook.isOnLoan()) {
			ui.Display("Book has not been borrowed");
      
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()){
			overDueFine = library.calculateOverDueFine(currentLoan);
		}
		ui.Display("Inspecting");
		ui.Display(currentBook.toString());
		ui.Display(currentLoan.toString());
		
		if (currentLoan.isOverDue()){
			ui.Display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		ui.setState(returnBookUi.uiState.INSPECTING);
		state = controlState.INSPECTING;		
	}


	public void scanningComplete() {
		if (!state.equals(controlState.READY)) {
			throw new runtimeException("returnBookControl: cannot call scanningComplete except in READY state");
		}
		ui.setState(returnBookUi.uiState.COMPLETED);		
	}


	public void dischargeLoan(boolean isDamaged) {
		if (!state.equals(controlState.INSPECTING)){ 
			throw new runtimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		ui.setState(returnBookUi.uiState.READY);
		state = controlState.READY;				
	}


}
