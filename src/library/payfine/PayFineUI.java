package library.payfine;
import java.util.Scanner;


public class PayFineUI {


	public static enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };

	private PayFineControl control;
	private Scanner input;
	private UiState state;

	
	public PayFineUI(PayFineControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UiState.INITIALISED;
		control.SeT_uI(this);
	}
	
	
	public void SetState(UiState state) {
		this.state = state;
	}


	public void Run() {
		Output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String Mem_Str = input("Swipe member card (press <enter> to cancel): ");
				if (Mem_Str.length() == 0) {
					control.CaNcEl();
					break;
				}
				try {
					int Member_ID = Integer.valueOf(Mem_Str).intValue();
					control.CaRd_sWiPeD(Member_ID);
				}
				catch (NumberFormatException e) {
					Output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double AmouNT = 0;
				String Amt_Str = input("Enter amount (<Enter> cancels) : ");
				if (Amt_Str.length() == 0) {
					control.CaNcEl();
					break;
				}
				try {
					AmouNT = Double.valueOf(Amt_Str).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (AmouNT <= 0) {
					Output("Amount must be positive");
					break;
				}
				control.PaY_FiNe(AmouNT);
				break;
								
			case CANCELLED:
				Output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				Output("Pay Fine process complete");
				return;
			
			default:
				Output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void Output(Object object) {
		System.out.println(object);
	}	
			

	public void Display(Object object) {
		Output(object);
	}


}
