/*

Author: Usama Bin Saleem
ID: 11697449
Course: ITC-515 Professional Programming Practice
Mediator Shamal Rathnayaka
Reviewer: Sanka Rathnayaka

*/

package library.returnBook;
import java.util.Scanner;


public class returnBookUI {

	public static enum UI_State { INITIALISED, READY, INSPECTING, COMPLETED };

	private returnBookControl Control;
	private Scanner input;
	private UI_state state;

	
	public returnBookUI(returnBookControl Control) {
		this.Control = Control;
		input = new Scanner(System.in);
		StAte = UI_state.INITIALISED;
		ControL.setUI(this);
	}


	public void Run() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY:
				String bookInputString = input("Scan Book (<enter> completes): ");
				if (bookInputString.length() == 0) 
					ControL.scanningComplete();
				
				else {
					try {
						int bookId = Integer.valueOf(bookInputString).intValue();
						Control.bookScanned(bookId);
					}
					catch (numberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String Ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;
				if (Ans.toUpperCase().equals("Y")){ 					
					isDamaged = true;
				}
				Control.dischargeLoan(isDamaged);
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new runtimeException("returnBookUI : unhandled state :" + StATe);			
			}
		}
	}

	
	private String input(String Prompt) {
		System.out.print(Prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void display(Object object) {
		output(object);
	}
	
	public void set_state(UI_state state) {
		this.state = state;
	}

	
}
