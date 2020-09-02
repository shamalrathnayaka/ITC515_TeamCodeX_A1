package library.payfine;
import library.entities.Library;
import library.entities.Member;

public class PayFineControl {
	
	private PayFineUI Ui;
	private enum cOnTrOl_sTaTe { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private cOnTrOl_sTaTe StAtE;
	
	private Library LiBrArY;
	private Member MeMbEr;


	public PayFineControl() {
		this.LiBrArY = Library.getInstance();
		StAtE = cOnTrOl_sTaTe.INITIALISED;
	}
	
	
	public void SeT_uI(PayFineUI uI) {
		if (!StAtE.equals(cOnTrOl_sTaTe.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.Ui = uI;
		uI.SetState((PayFineUI.UiState.READY));
		StAtE = cOnTrOl_sTaTe.READY;		
	}


	public void CaRd_sWiPeD(int MeMbEr_Id) {
		if (!StAtE.equals(cOnTrOl_sTaTe.READY)) 
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
			
		MeMbEr = LiBrArY.getMember(MeMbEr_Id);
		
		if (MeMbEr == null) {
			Ui.Display("Invalid Member Id");
			return;
		}
		Ui.Display(MeMbEr.toString());
		Ui.SetState(PayFineUI.UiState.PAYING);
		StAtE = cOnTrOl_sTaTe.PAYING;
	}
	
	
	public void CaNcEl() {
		Ui.SetState(PayFineUI.UiState.CANCELLED);
		StAtE = cOnTrOl_sTaTe.CANCELLED;
	}


	public double PaY_FiNe(double AmOuNt) {
		if (!StAtE.equals(cOnTrOl_sTaTe.PAYING)) 
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
			
		double ChAnGe = MeMbEr.payFine(AmOuNt);
		if (ChAnGe > 0) 
			Ui.Display(String.format("Change: $%.2f", ChAnGe));
		
		Ui.Display(MeMbEr.toString());
		Ui.SetState(PayFineUI.UiState.COMPLETED);
		StAtE = cOnTrOl_sTaTe.COMPLETED;
		return ChAnGe;
	}
	


}
