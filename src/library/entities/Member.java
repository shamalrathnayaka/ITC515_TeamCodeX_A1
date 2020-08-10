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
/*
orignal
	private String LaSt_NaMe;
	private String FiRsT_NaMe;
	private String EmAiL_AdDrEsS;
	private int PhOnE_NuMbEr;
	private int MeMbEr_Id;
	private double FiNeS_OwInG;
	
	private Map<Integer, Loan> cUrReNt_lOaNs;
*/
	// Edited
	private String Last_Name;
	private String FirsT_Name;
	private String EmaiL_Address;
	private int Phone_Number;
	private int Member_Id;
	private double Fines_Owing;
	
	private Map<Integer, Loan> Current_Loans;

	/*
	Orignal
	
	public Member(String lAsT_nAmE, String fIrSt_nAmE, String eMaIl_aDdReSs, int pHoNe_nUmBeR, int mEmBeR_iD) {
		this.LaSt_NaMe = lAsT_nAmE;
		this.FiRsT_NaMe = fIrSt_nAmE;
		this.EmAiL_AdDrEsS = eMaIl_aDdReSs;
		this.PhOnE_NuMbEr = pHoNe_nUmBeR;
		this.MeMbEr_Id = mEmBeR_iD;
		
		this.cUrReNt_lOaNs = new HashMap<>();
		
		*/
	
	//Edited
	
	public Member(String LasT_Name, String First_Name, String Email_Address, int Phone_Number, int Member_Id) {
		this.Last_Name = Last_Name;
		this.FirsT_Name = First_Name;
		this.Email_Address = email_address;
		this.Phone_Number = Phone_Number;
		this.Member_Id = Member_Id;
		
		this.cUrReNt_lOaNs = new hashMap<>();

	}

	/*
	Orignal
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(MeMbEr_Id).append("\n")
		  .append("  Name:  ").append(LaSt_NaMe).append(", ").append(FiRsT_NaMe).append("\n")
		  .append("  Email: ").append(EmAiL_AdDrEsS).append("\n")
		  .append("  Phone: ").append(PhOnE_NuMbEr)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", FiNeS_OwInG))
		  .append("\n");
		
		for (Loan LoAn : cUrReNt_lOaNs.values()) {
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	*/
	
	//Edited
	
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

	/*
	Orignal
	
	public int GeT_ID() {
		return MeMbEr_Id;
	}

	
	public List<Loan> GeT_LoAnS() {
		return new ArrayList<Loan>(cUrReNt_lOaNs.values());
	}

	
	public int gEt_nUmBeR_Of_CuRrEnT_LoAnS() {
		return cUrReNt_lOaNs.size();
	}

	
	public double FiNeS_OwEd() {
		return FiNeS_OwInG;
	}

	*/
	
	//Edited
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
	
	
	/*
	Orignal
	
	public void TaKe_OuT_LoAn(Loan lOaN) {
		if (!cUrReNt_lOaNs.containsKey(lOaN.GeT_Id())) 
			cUrReNt_lOaNs.put(lOaN.GeT_Id(), lOaN);
		
		else 
			throw new RuntimeException("Duplicate loan added to member");
				
	}

	
	public String GeT_LaSt_NaMe() {
		return LaSt_NaMe;
	}

	
	public String GeT_FiRsT_NaMe() {
		return FiRsT_NaMe;
	}


	public void AdD_FiNe(double fine) {
		FiNeS_OwInG += fine;
	}
	
	public double PaY_FiNe(double AmOuNt) {
		if (AmOuNt < 0) 
			throw new RuntimeException("Member.payFine: amount must be positive");
		
		double change = 0;
		if (AmOuNt > FiNeS_OwInG) {
			change = AmOuNt - FiNeS_OwInG;
			FiNeS_OwInG = 0;
		}
		else 
			FiNeS_OwInG -= AmOuNt;
		
		return change;
	}


	public void dIsChArGeLoAn(Loan LoAn) {
		if (cUrReNt_lOaNs.containsKey(LoAn.GeT_Id())) 
			cUrReNt_lOaNs.remove(LoAn.GeT_Id());
		
		else 
			throw new RuntimeException("No such loan held by member");
				
	}

}
*/
	
///Edited
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
