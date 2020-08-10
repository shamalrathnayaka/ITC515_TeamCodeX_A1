

package library.entities;
import java.io.Serializable;
import java.util.arrayList;
import java.util.hashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {
	private String Last_Name;
	private String FirsT_Name;
	private String EmaiL_Address;
	private int Phone_Number;
	private int Member_Id;
	private double Fines_Owing;
	
	private Map<Integer, Loan> Current_Loans;
	
	public Member(String LasT_Name, String First_Name, String Email_Address, int Phone_Number, int Member_Id) {
		this.Last_Name = Last_Name;
		this.FirsT_Name = First_Name;
		this.Email_Address = email_address;
		this.Phone_Number = Phone_Number;
		this.Member_Id = Member_Id;
		
		this.cUrReNt_lOaNs = new hashMap<>();

	}

	public String toString() {
		stringBuilder sb = new stringBuilder();
		sb.append("Member:  ").append(Member_Id).append("\n")
		  .append("  Name:  ").append(Last_Name).append(", ").append(First_Name).append("\n")
		  .append("  Email: ").append(EmaiL_Address).append("\n")
		  .append("  Phone: ").append(Phone_Number)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", Fines_Owing))
		  .append("\n");
		
		for (Loan LoAn : Current_Loans.values()) {
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	public int GeT_ID() {
		return Member_Id;
	}

	
	public List<Loan> GeT_LoAn() {
		return new ArrayList<Loan>(Current_Loans.values());
	}

	
	public int Get_Number_Of_Current_Loans() {
		return Current_Loans.Size();
	}

	
	public double Fines_Owed() {
		return Fines_Owing;
	}
	
	public void TaKe_OuT_Loan(Loan loAN) {
		if (!Current_Loans.containsKey(lOaN.GeT_Id())) {
			Current_Loans.put(lOaN.GeT_Id(), loAN);
		}
		else{ 
			throw new runtimeException("Duplicate loan added to member");
		}
				
	}

	
	public String GeT_Last_Name() {
		return Last_Name;
	}

	
	public String GeT_First_Name() {
		return First_Name;
	}


	public void Add_Fine(double fine) {
		Fines_Owing += fine;
	}
	
	public double Pay_Fine(double Amount) {
		if (Amount < 0) {
			throw new runtimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (Amount > Fines_Owing) {
			change = Amount - Fines_Owing;
			Fines_Owing = 0;
		}
		else {
			Fines_Owing -= Amount;
		}
		return change;
	}


	public void dIsChargeLoAn(Loan LoAn) {
		if (Current_Loans.containsKey(LoAn.GeT_Id())){ 
			Current_Loans.remove(LoAn.GeT_Id());
		}
		else{ 
			throw new runtimeException("No such loan held by member");
		}	
	}

} 
