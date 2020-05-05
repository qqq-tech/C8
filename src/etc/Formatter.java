package etc;

public class Formatter {

	//ex String number = String.format("%03d", i);
	//ex 10의자리수 0 채우기: %03d, 소수점 자리:"%.2f"
	public static String getFormatString(String format,int i)
	{
		String number = String.format(format, i);
		return number;
	}
}
