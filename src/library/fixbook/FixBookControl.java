package library.fixbook;
import library.entities.Book;
import library.entities.Library;

public class FixBookControl {
	
	private FixBookUi ui;
	private enum ControlState { INITIALISED, READY, FIXING };
	private ControlState State;
	
	private Library library;
	private Book currentBook;


	public FixBookControl() {
		this.library = Library.getInstance();
		State = ControlState.INITIALISED;
	}
	
	
	public void SetUi(FixBookUi ui) {
		if (!State.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}
		this.ui = ui;
		ui.setState(FixBookUi.Uistate.READY);
		State = ControlState.READY;		
	}


	public void BookScanned(int BookId) {
		if (!State.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}
		currentBook = library.getBook(BookId);
		
		if (currentBook == null) {
			ui.display("Invalid bookId");
			return;
		}

		if (!currentBook.isDamaged()) {

			ui.display("Book has not been damaged");
			return;
		}
		ui.display(currentBook.toString());
		ui.setState(FixBookUi.Uistate.FIXING);
		State = ControlState.FIXING;		
	}


	public void FixBook(boolean mustFix) {
		if (!State.equals(ControlState.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}
		if (mustFix) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		ui.setState(FixBookUi.Uistate.READY);
		State = ControlState.READY;		
	}

	
	public void ScanningComplete() {
		if (!State.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}
		ui.setState(FixBookUi.Uistate.COMPLETED);		
	}

}
