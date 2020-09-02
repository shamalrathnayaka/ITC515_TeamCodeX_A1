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

	private returnBookUI Ui;
	private enum controlState { INITIALISED, READY, INSPECTING };
	private controlState state;
	
	private Library library;
	private Loan currentLoan;
	

	public returnBookControl() {
		this.library = library.getInstance();
		state = controlState.INITIALISED;
	}
	
	
	public void setUi(returnBookUI Ui) {
		if (!state.equals(controlState.INITIALISED)) {
			throw new RuntimeException("returnBookControl: cannot call setUI except in INITIALISED state");
		}
		this.Ui = Ui;
		Ui.setState(returnBookUI.uiState.READY);
		state = controlState.READY;		
	}


	public void bookScanned(int bookId) {
		if (!state.equals(controlState.READY)){ 
			throw new RuntimeException("returnBookControl: cannot call bookScanned except in READY state");
		}
		Book currentBooK = library.getBook(bookId);
		
		if (currentBooK == null) {
			Ui.display("Invalid Book Id");
			return;
		}

		if (!currentBooK.isOnLoan()) {
			Ui.display("Book has not been borrowed");
      
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()){
			overDueFine = library.calculateOverDueFine(currentLoan);
		}
		Ui.display("Inspecting");
		Ui.display(currentBooK.toString());
		Ui.display(currentLoan.toString());
		
		if (currentLoan.isOverDue()){
			Ui.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		Ui.setState(returnBookUI.uiState.INSPECTING);
		state = controlState.INSPECTING;		
	}


	public void scanningComplete() {
		if (!state.equals(controlState.READY)) {
			throw new RuntimeException("returnBookControl: cannot call scanningComplete except in READY state");
		}
		Ui.setState(returnBookUI.uiState.COMPLETED);
	}


	public void dischargeLoan(boolean isDamaged) {
		if (!state.equals(controlState.INSPECTING)){ 
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		Ui.setState(returnBookUI.uiState.READY);
		state = controlState.READY;				
	}


}
