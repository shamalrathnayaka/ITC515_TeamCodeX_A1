/**
 *
 * @Author: Usama Bin Saleem
 * @SID: 11697449
 * @Subject: ITC515 - Professional Programming Practice
 * @Team: TeamCodeX
 */

package library.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {

	private String lastName;
	private String firstName;
	private String emailAddress;
	private int phoneNumber;
	private int memberId;
	private double finesOwing;
	
	private Map<Integer, Loan> currentLoans;

	
	public Member(String lastName, String firstName, String emailAddress, int phoneNumber, int memberId) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.memberId = memberId;
		
		this.currentLoans = new HashMap<>();

	}

	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(memberId).append("\n")
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
		  .append("  Email: ").append(emailAddress).append("\n")
		  .append("  Phone: ").append(phoneNumber)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", finesOwing))
		  .append("\n");
		
		for (Loan LoAn : currentLoans.values()) {
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	public int getID() {
		return memberId;
	}

	
	public List<Loan> getLoan() {
		return new ArrayList<Loan>(currentLoans.values());
	}

	
	public int getNumberOfcurrentLoans() {
		return currentLoans.size();
	}

	
	public double finesOwed() {
		return finesOwing;
	}
	
	
	
	public void TaKe_Out_Loan(Loan loan) {
		if (!currentLoans.containsKey(loan.GetId())) {
			currentLoans.put(loan.GetId(), loan);
		}
		else{ 
			throw new RuntimeException("Duplicate loan added to member");
		}
				
	}

	
	public String getlastName() {
		return lastName;
	}

	
	public String getfirstName() {
		return firstName;
	}


	public void addFine(double fine) {
		finesOwing += fine;
	}
	
	public double payFine(double Amount) {
		if (Amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (Amount > finesOwing) {
			change = Amount - finesOwing;
			finesOwing = 0;
		}
		else {
			finesOwing -= Amount;
		}
		return change;
	}


	public void dischargeLoAn(Loan LoAn) {
		if (currentLoans.containsKey(LoAn.GetId())){
			currentLoans.remove(LoAn.GetId());
		}
		else{ 
			throw new RuntimeException("No such loan held by member");
		}	
	}

} 
