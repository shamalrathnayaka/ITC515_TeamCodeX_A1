package library.fixbook;
import library.entities.Book;
import library.entities.Library;

public class FixBookControl {
	
	private FixBookUI ui;
	private enum ControlState { INITIALISED, READY, FIXING };
	private ControlState State;
	
	private Library library;
	private Book currentBook;


	public FixBookControl() {
		this.library = Library.getInstance();
		State = ControlState.INITIALISED;
	}
	
	
	public void SetUi(FixBookUI ui) {
		if (!State.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}
		this.ui = ui;
		ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
		State = ControlState.READY;		
	}


	public void BookScanned(int BookId) {
		if (!State.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}
		currentBook = library.getBook(BookId);
		
		if (currentBook == null) {
			ui.dIsPlAy("Invalid bookId");
			return;
		}

		if (!currentBook.isDamaged()) {

			ui.dIsPlAy("Book has not been damaged");
			return;
		}
		ui.dIsPlAy(currentBook.toString());
		ui.SeT_StAtE(FixBookUI.uI_sTaTe.FIXING);
		State = ControlState.FIXING;		
	}


	public void FiX_BoOk(boolean mUsT_FiX) {
		if (!State.equals(ControlState.FIXING)) 
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
			
		if (mUsT_FiX) 
			library.repairBook(currentBook);
		
		currentBook = null;
		ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
		State = ControlState.READY;		
	}

	
	public void SCannING_COMplete() {
		if (!State.equals(ControlState.READY)) 
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
			
		ui.SeT_StAtE(FixBookUI.uI_sTaTe.COMPLETED);		
	}

}
