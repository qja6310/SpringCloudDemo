package cn.com.newloading.common.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	/**
	 * Date转String
	 * @param time  时间
	 * @param format  输出格式
	 * @return
	 */
	public static String dateToString(Date time,String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(time);
		return dateString;
	}

	/**
	 * String转Date
	 * @param time  时间
	 * @param format  时间格式
	 * @return
	 */
	public static Date stringToDate(String time,String format) {
		DateFormat df = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间格式转换
	 * @param time
	 * @param iFormat  输入格式
	 * @param oFormat  输出格式
	 * @return
	 */
	public static String dataFormatting(String time,String iFormat,String oFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(iFormat, Locale.ENGLISH);
		SimpleDateFormat formatter = new SimpleDateFormat(oFormat);
		Date date = null;
		try {
			date = sdf.parse(time);
			time = formatter.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
}
