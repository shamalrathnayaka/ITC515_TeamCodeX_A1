package library.payfine;
import library.entities.Library;
import library.entities.Member;

public class PayFineControl {
	
	private PayFineUI ui;
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private ControlState state;
	
	private Library library;
	private Member member;


	public PayFineControl() {
		this.LiBrArY = Library.getInstance();
		state = cOnTrOl_sTaTe.INITIALISED;
	}
	
	
	public void SetUI(PayFineUI ui) {
		if (!state.equals(cOnTrOl_sTaTe.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.SeT_StAtE(PayFineUI.uI_sTaTe.READY);
		state = cOnTrOl_sTaTe.READY;
	}


	public void CaRd_sWiPeD(int MeMbEr_Id) {
		if (!state.equals(cOnTrOl_sTaTe.READY))
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
			
		MeMbEr = LiBrArY.getMember(MeMbEr_Id);
		
		if (MeMbEr == null) {
			ui.DiSplAY("Invalid Member Id");
			return;
		}
		ui.DiSplAY(MeMbEr.toString());
		ui.SeT_StAtE(PayFineUI.uI_sTaTe.PAYING);
		state = cOnTrOl_sTaTe.PAYING;
	}
	
	
	public void CaNcEl() {
		ui.SeT_StAtE(PayFineUI.uI_sTaTe.CANCELLED);
		state = cOnTrOl_sTaTe.CANCELLED;
	}


	public double PaY_FiNe(double AmOuNt) {
		if (!state.equals(cOnTrOl_sTaTe.PAYING))
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
			
		double ChAnGe = MeMbEr.payFine(AmOuNt);
		if (ChAnGe > 0)
			ui.DiSplAY(String.format("Change: $%.2f", ChAnGe));

		ui.DiSplAY(MeMbEr.toString());
		ui.SeT_StAtE(PayFineUI.uI_sTaTe.COMPLETED);
		state = cOnTrOl_sTaTe.COMPLETED;
		return ChAnGe;
	}
	


}
