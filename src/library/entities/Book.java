package library.entities;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title;
	private String author;
	private String callNo;
	private int id;
	
	private enum sTaTe { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private sTaTe state;
	
	
	public Book(String author, String title, String callNo, int id) {
		this.author = author;
		this.title = title;
		this.callNo = callNo;
		this.id = id;
		this.state = sTaTe.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n")
		  .append("  Title:  ").append(title).append("\n")
		  .append("  Author: ").append(author).append("\n")
		  .append("  CallNo: ").append(callNo).append("\n")
		  .append("  State:  ").append(state);
		
		return sb.toString();
	}

	public Integer gEtId() {
		return id;
	}

	public String gEtTiTlE() {
		return title;
	}


	
	public boolean iS_AvAiLaBlE() {
		return state == sTaTe.AVAILABLE;
	}

	
	public boolean iS_On_LoAn() {
		return state == sTaTe.ON_LOAN;
	}

	
	public boolean iS_DaMaGeD() {
		return state == sTaTe.DAMAGED;
	}

	
	public void BoRrOw() {
		if (state.equals(sTaTe.AVAILABLE)) 
			state = sTaTe.ON_LOAN;
		
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		
		
	}


	public void ReTuRn(boolean DaMaGeD) {
		if (state.equals(sTaTe.ON_LOAN)) 
			if (DaMaGeD) 
				state = sTaTe.DAMAGED;
			
			else 
				state = sTaTe.AVAILABLE;
			
		
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
				
	}

	
	public void RePaIr() {
		if (state.equals(sTaTe.DAMAGED)) 
			state = sTaTe.AVAILABLE;
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		
	}


}
