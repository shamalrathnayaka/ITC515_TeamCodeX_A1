package library.entities;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self;
	private static java.util.Calendar calender;
	
	
	private Calendar() {
		cAlEnDaR = java.util.Calendar.getInstance();
	}
	
	public static Calendar gEtInStAnCe() {
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		calender.add(java.util.Calendar.DATE, days);
	}
	
	public synchronized void SeT_DaTe(Date DaTe) {
		try {
			calender.setTime(DaTe);
	        calender.set(java.util.Calendar.HOUR_OF_DAY, 0);
	        calender.set(java.util.Calendar.MINUTE, 0);
	        calender.set(java.util.Calendar.SECOND, 0);
	        calender.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date gEt_DaTe() {
		try {
	        calender.set(java.util.Calendar.HOUR_OF_DAY, 0);
	        calender.set(java.util.Calendar.MINUTE, 0);
	        calender.set(java.util.Calendar.SECOND, 0);
	        calender.set(java.util.Calendar.MILLISECOND, 0);
			return calender.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date gEt_DuE_DaTe(int loanPeriod) {
		Date nOw = gEt_DaTe();
		calender.add(java.util.Calendar.DATE, loanPeriod);
		Date dUeDaTe = calender.getTime();
		calender.setTime(nOw);
		return dUeDaTe;
	}
	
	public synchronized long GeT_DaYs_DiFfErEnCe(Date targetDate) {
		
		long Diff_Millis = gEt_DaTe().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
