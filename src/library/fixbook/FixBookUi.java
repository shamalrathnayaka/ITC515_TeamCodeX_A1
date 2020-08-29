package library.fixbook;
import java.util.Scanner;


public class FixBookUi {

	public static enum Uistate { INITIALISED, READY, FIXING, COMPLETED };

	private FixBookControl control;
	private Scanner input;
	private Uistate state;

	
	public FixBookUi(FixBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = Uistate.INITIALISED;
		control.SetUi(this);
	}


	public void setState(Uistate state) {
		this.state = state;
	}

	
	public void run() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String BoOk_EnTrY_StRiNg = input("Scan Book (<enter> completes): ");
				if (BoOk_EnTrY_StRiNg.length() == 0) 
					control.ScanningComplete();
				
				else {
					try {
						int BoOk_Id = Integer.valueOf(BoOk_EnTrY_StRiNg).intValue();
						control.BookScanned(BoOk_Id);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String AnS = input("Fix Book? (Y/N) : ");
				boolean FiX = false;
				if (AnS.toUpperCase().equals("Y")) 
					FiX = true;
				
				control.FixBook(FiX);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}
		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		output(object);
	}
	
	
}
