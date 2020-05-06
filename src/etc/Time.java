package etc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Time {
	public static Date addTime(Date date,int sec) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, sec);
		return cal.getTime();
	}

	public static Date getStringToTime(String time) {
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
		Date d1 = null;
		try {
			d1 = f.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d1;
	}
	
	public static String getTimeToString(Date time) {
		//yyyy-MM-dd HH:mm:ss
		SimpleDateFormat transFormat = new SimpleDateFormat("HH:mm:ss");
		String to = transFormat.format(time);
		return to;
	}

	public static long getDiffTime(Date d1, Date d2) {
		long diff = d1.getTime() - d2.getTime();
		long sec = diff / 1000;
		return sec;
	}
}
