package com.zzy;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtil {
	/**默认的格式化方式*/
	private static final String defaultFormat = "yyyy-MM-dd HH:mm:ss";
	/**
	 * Field value: Year
	 */
	public static final int YEAR = 1;

	/**
	 * Field value: Month
	 */
	public static final int MONTH = 2;

	/**
	 * Field value: Day
	 */
	public static final int DAY = 3;
	/**
	 * Field value: Week 周
	 */
	public static final int WEEK = 4;

	/**
	 * Field value: Hour
	 */
	public final static int HOUR = 10;

	/**
	 * Field value: Hour of Day
	 */
	public final static int HOUR_OF_DAY = 11;

	/**
	 * Field value: Minute
	 */
	public final static int MINUTE = 12;

	/**
	 * Field value: Second
	 */
	public final static int SECOND = 13;
	/**
	 * 获得本年第一天日期
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static String getDateString(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	public static String fixTimestamp(String str) {
		if (str.indexOf(':') == -1) {
			return qualify(str) + " 00:00:00";
		} else {
			int i = str.indexOf(' ');
			return qualify(str.substring(0, i)) + str.substring(i);
		}
	}

	private static String qualify(String dateStr) {
		if (dateStr.length() == 10) {
			return dateStr;
		}
		String[] sec = dateStr.split("-");
		if (sec.length == 3) {
			StringBuilder buf = new StringBuilder(10);
			buf.append(sec[0]);
			buf.append("-");
			if (sec[1].length() == 1)
				buf.append("0");
			buf.append(sec[1]);
			buf.append("-");
			if (sec[2].length() == 1)
				buf.append("0");
			buf.append(sec[2]);
			return buf.toString();
		} else
			return dateStr;
	}

	public static String fixTime(String str) {
		if (str.indexOf(':') == -1)
			return "00:00:00";
		int b = str.indexOf(' '), e = str.indexOf('.');
		if (b == -1)
			b = 0;
		if (e == -1)
			e = str.length();
		return str.substring(b, e);
	}


	public static int daysInMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int dayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int yearOf(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public static int dayOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	public static int dayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static String toString(Date date) {
		if (date == null) {
			return "";
		}
		Timestamp t = new Timestamp(date.getTime());
		return t.toString();
	}

	public static Date incYear(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	public static Date incMonth(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	public static int hourOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}



	public static Date incDay(Date date, long days) {
		return new Date(date.getTime() + 86400000 * days);
	}

	public static Date incSecond(Date date, long seconds) {
		return new Date(date.getTime() + 1000 * seconds);
	}

	public static Boolean isSameDay(Date date1,Date date2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		String s1 = sdf.format(date1);
		String s2 = sdf.format(date2);
		if(s1.equals(s2)) {
			return true;
		} else {
			return false;
		}
	}

	public static int getWeekNumOfYear(String dateStr){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);

		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 以指定格式返回当前时间的字符串表现形式
	 * @param
	 * @return String
	 *
	 */
	public static String getCurrentDate() {
		String format="yyyy-MM-dd";
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		if (format == null || "".equals(format.trim())) {
			format = defaultFormat;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	public static String getCurrentTime() {
		String format="yyyyMMddHHmmss";
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		if (format == null || "".equals(format.trim())) {
			format = defaultFormat;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 *
	 * @param d1
	 * @param
	 * @return
	 * String 转 date ""yyyy-MM-dd HH:mm:ss  对应截取
	 */
	public static Date stringToDate(String d1,String formate){
		Date d2 = null  ;
		try {
			DateFormat df = new SimpleDateFormat (formate.trim());
			d2 = df.parse(d1.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d2;
	}

	public static String TimeDifference(String FirstDate,String secondDate){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String TimeDifference = "";
		try
		{

			Date d1 = df.parse(FirstDate);  //后的时间
			Date d2 = df.parse(secondDate); //前的时间
			Long diff = d1.getTime() - d2.getTime();   //两时间差，精确到毫秒

			Long day = diff / (1000 * 60 * 60 * 24);          //以天数为单位取整
			Long hour=(diff/(60*60*1000)-day*24);             //以小时为单位取整
			Long min=((diff/(60*1000))-day*24*60-hour*60);    //以分钟为单位取整
			Long secone=(diff/1000-day*24*60*60-hour*60*60-min*60);



			System.out.println("---diff的值---->" +diff);
			System.out.println("---days的值---->" +day);
			System.out.println("---hour的值---->" +hour);
			System.out.println("---min的值---->"  +min);
			System.out.println("---secone的值---->"  +secone);

			System.out.println("---两时间差---> " +day+"天"+hour+"小时"+min+"分"+secone+"秒");

			TimeDifference =  hour+":"+min+":"+secone;
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		return TimeDifference;


	}

	/**
	 * Add value on special field of date
	 *
	 * @param iField
	 *            Field which need add value
	 * @param iValue
	 *            Value which will be added
	 * @param date
	 *            Basic date
	 * @return New date
	 */
	public static Date dateAdd(int iField, int iValue, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch (iField) {
			case DateUtil.YEAR:
				cal.add(Calendar.YEAR, iValue);
				break;
			case DateUtil.MONTH:
				cal.add(Calendar.MONTH, iValue);
				break;
			case DateUtil.DAY:
				cal.add(Calendar.DATE, iValue);
				break;
			case DateUtil.HOUR:
				cal.add(Calendar.HOUR, iValue);
				break;
			case DateUtil.HOUR_OF_DAY:
				cal.add(Calendar.HOUR_OF_DAY, iValue);
				break;
			case DateUtil.MINUTE:
				cal.add(Calendar.MINUTE, iValue);
				break;
			case DateUtil.SECOND:
				cal.add(Calendar.SECOND, iValue);
				break;
			case DateUtil.WEEK:
				cal.add(Calendar.DATE, iValue*7);
				break;
			default:
				break;
		}
		return cal.getTime();
	}

	/**
	 * 根据星期获取当前周的所在日期
	 * @param xingqi 星期：1，2，3，4，5，6，7
	 * @return
	 */
	public static String getWeekOfXQ(int xingqi){
		if(xingqi<1 || xingqi>7){
			return "";
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_WEEK, (2-cal.get(Calendar.DAY_OF_WEEK))%7);
		List list = new ArrayList();
		SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<7;i++){
			list.add(d.format(cal.getTime()));
			cal.roll(Calendar.DAY_OF_YEAR,true);
		}
		return list.get(xingqi-1).toString();
	}

	public static int daysBetween(Date smdate,Date bdate) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static int getIntervalDays(Date fDate, Date oDate) {
		if (null == fDate || null == oDate) {
			return -1;
		}
		long intervalMilli = (fDate.getTime() - oDate.getTime())/1000;
		return (int) (intervalMilli / (24 * 60 * 60));

	}


	public static void main(String[] args) {
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH,12-1);
		try {
			System.out.println(daysBetween(stringToDate("2016-05-03", "yyyy-MM-dd"),stringToDate("2016-05-05", "yyyy-MM-dd")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		System.out.println(daysInMonth(new Date()));
	}

}
