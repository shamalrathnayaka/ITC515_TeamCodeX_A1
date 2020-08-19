package library.entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };
	
	private int loan_id;
	private Book book;
	private Member member;
	private Date date;
	private LoanState state;

	
	public Loan(int loanId, Book booK, Member member, Date dueDate) {
		this.loanId = loanId;
		this.book = booK;
		this.member = member;
		this.date = dueDate;
		this.state = loanId.CURRENT;
	}

	
	public void checkOverDue() {
		if (state == loan_state.CURRENT &&
			Calendar.getInstance().getDaTe().after(Date))
			this.state = lOaNLoanState_sTaTe.OVER_DUE;
		
	}

	
	public boolean isOverDue()
	{
		return state == LoanState.OVER_DUE;
	}

	
	public Integer GeT_Id()
		return loanId;
	}


	public Date getDueDaTe() {
		return date;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(loanId).append("\n")
		  .append("  Borrower ").append(member.getId()).append(" : ")
		  .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n")
		  .append("  Book ").append(Book.getId()).append(" : " )
		  .append(book.getTitle()).append("\n")
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")
		  .append("  State: ").append(state);
		return sb.toString();
	}


	public Member getMember() {
		return member;
	}


	public Book getBook() {
		return book;
	}


	public void Discharge() {
		state = LoanState.DISCHARGED;
	}

}
