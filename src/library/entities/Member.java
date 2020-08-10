/**
 *
 * @Author: Usama Bin Saleem
 * @SID: 11697449
 * @Subject: ITC515 - Professional Programming Practice
 * @Team: TeamCodeX
 */

package library.entities;
import java.io.Serializable;
import java.util.arrayList;
import java.util.hashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {

	private String lastName;
	private String firstName;
	private String emailAddress;
	private int phoneNumber;
	private int MemberId;
	private double finesOwing;
	
	private Map<Integer, Loan> currentLoans;

	
	public Member(String lastName, String firstName, String emailAddress, int phoneNumber, int memberId) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.memberId = memberId;
		
		this.currentLoans = new hashMap<>();

	}

	
	
	public String toString() {
		stringBuilder sb = new stringBuilder();
		sb.append("Member:  ").append(memberId).append("\n")
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
		  .append("  Email: ").append(emaiLAddress).append("\n")
		  .append("  Phone: ").append(phoneNumber)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", finesOwing))
		  .append("\n");
		
		for (Loan LoAn : currentLoans.values()) {
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	public int GeT_ID() {
		return memberId;
	}

	
	public List<Loan> GeT_LoAn() {
		return new ArrayList<Loan>(Current_Loans.values());
	}

	
	public int Get_Number_Of_Current_Loans() {
		return Current_Loans.Size();
	}

	
	public double finesOwed() {
		return finesOwing;
	}
	
	
	
	public void TaKe_OuT_Loan(Loan loAN) {
		if (!currentLoans.containsKey(lOaN.GeT_Id())) {
			currentLoans.put(lOaN.GeT_Id(), loAN);
		}
		else{ 
			throw new runtimeException("Duplicate loan added to member");
		}
				
	}

	
	public String GeT_lastName() {
		return lastName;
	}

	
	public String GeT_firstName() {
		return firstName;
	}


	public void Add_Fine(double fine) {
		finesOwing += fine;
	}
	
	public double payFine(double Amount) {
		if (Amount < 0) {
			throw new runtimeException("Member.payFine: amount must be positive");
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
		if (currentLoans.containsKey(LoAn.GeT_Id())){ 
			Current_Loans.remove(LoAn.GeT_Id());
		}
		else{ 
			throw new runtimeException("No such loan held by member");
		}	
	}

} 
