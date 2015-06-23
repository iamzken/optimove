package com.topcheer.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtil {
	private static final String DATE_FORMAT = "yyyyMMdd";//日期变量
	private static final String TIME_FORMAT = "HHmmss";//时间变量
	public static Pattern ptDate = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|"
            + "(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|"
            + "([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|"
            + "([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
            + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
            + "((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|"
            + "(\\:([0-5]?[0-9])))))?$");
	
	/**
	 * 获取当前日期、时间
	 * @param dateRegex
	 * @param timeRegex
	 * @return
	 */
	public static String getCurrentDateTime(String dateRegex,String timeRegex) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + dateRegex + "MM" + dateRegex + "dd HH" + timeRegex + "mm" + timeRegex + "ss");
		return sdf.format(d);
	}
	
	/**
	 * 格式化日期
	 * @param dateRegex
	 * @param timeRegex
	 * @return
	 */
	public static String dateFormat(String date, String dateRegex,String timeRegex) {
		if(date == null || "".equals(date.trim())) return "";
		if(date.length() == 13) date = date +"00";
		
		DateFormat sourceFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		DateFormat format = new SimpleDateFormat("yyyy"+dateRegex+"MM"+dateRegex+"dd HH"+timeRegex+"mm"+timeRegex+"ss");
		try {
			return format.format(sourceFormat.parse(date));
		} catch (ParseException e) {
			System.out.println("日期转化格式错误("+date+"):"+e.getMessage());
		}
		return "";
	}
	
	/**
	 * 判断时间格式是否合符格式（yyyyMMdd）可以判断各种格式的日期和时间,日期中间可以用'-'、'/'或没有分隔符
	 * @param value
	 * @return
	 */
	public static boolean checkDataStyle(String value) {
		if(null == value){
			return false;
		}
		if(ptDate.matcher(value).matches()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取当前年份
	 * @return
	 */
	public static int getYear(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String timeNow = simpleDateFormat.format(date);
		return Integer.valueOf(timeNow).intValue();
	}
	
	/**
	 * 获取一年的总天数
	 * @return
	 */
	public static int getTotalDaysOfYear()
	{
		int days = 365;
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(getDateForString(getCurrentDate(),DATE_FORMAT));
		days = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
		return days;
	}
	
	/**
	 * 
	 * @param 日期转为字符串类型
	 * @param format 如 yyyyMMdd
	 * @return
	 */
	public static String getStringForDate(Date date,String format)
	{
		String tmp =null;
		SimpleDateFormat  dateFormat = new SimpleDateFormat (format);
		tmp = dateFormat.format(date);
		return tmp;
	}
	
	/**
	 * 字符串转为日期类型
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date getDateForString(String str,String format)
	{
		Date tmp =null;
		SimpleDateFormat  dateFormat = new SimpleDateFormat (format);
		try {
			tmp = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tmp;
	}
	
	/**
	 * 取得当前系统日期、时间
	 * @return 当前日期
	 */
	public static String getCurrentDateAndTime(String dateSplit, String timeSplit) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		Date date = new Date();
		try {
			date = simpleDateFormat.parse(getCurrentDate());
		} catch (ParseException e) {
			System.out.println("date格式转化异常" + e.getMessage());
		}
		simpleDateFormat = new SimpleDateFormat("yyyy"+dateSplit+"MM"+dateSplit+"dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH"+timeSplit+"mm"+timeSplit+"ss");
		
		return simpleDateFormat.format(date) + " " + timeFormat.format(new Date()) ;
	}
	
	/**
	 * 取得当前系统日期（分隔符）
	 * @return 当前日期
	 */
	public static String getCurrentDateWithSplit(String split) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		Date date = new Date();
		try {
			date = simpleDateFormat.parse(getCurrentDate());
		} catch (ParseException e) {
			System.out.println("date格式转化异常" + e.getMessage());
		}
		simpleDateFormat = new SimpleDateFormat("yyyy"+split+"MM"+split+"dd");
		
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 取得当前系统日期
	 * @return 当前日期
	 */
	public static String getCurrentDate() {
		String sDate="";
		String projectDate = null; //ParaHelper.getPath("PROJECT_DATE");
		if(projectDate !=null && !"".equals(projectDate) && isValidDate(projectDate)){
			sDate = projectDate;
		}else{
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			Date today = new Date();
			sDate = dateFormat.format(today);
		}
		return sDate;
	}
	
	/**
	 * 取得当前理财系统时间
	 * 
	 * @return 当前时间
	 */
	public static String getCurrentTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
		Date today = new Date();
		String sDate = dateFormat.format(today);
		return sDate;
	}

	/**
	 * 验证日期格式是否合法
	 * @param s
	 * @return
	 */
	public static boolean isValidDate(String s) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			dateFormat.setLenient(false);
			dateFormat.parse(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String getCurrentTransTime(){
		return DateUtil.getCurrentDateAndTime("/", ":");
	}
	
	public static String getSystemCurrentTime(String transDateAndTime){
		return transDateAndTime.split(" ")[1].replace(":", "");
	}
	
	public static String getSystemCurrentDate(String transDateAndTime){
		return transDateAndTime.split(" ")[0].replace("/", "");
	}
	
	/**
	 * 获得指定日期的前一天
	 * yyyyMMdd
	 * @param DayVal
	 * @return
	 * @throws Exception
	 */
	public static String getDayBefore(String format, String DayVal) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(DayVal);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat(format).format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * yyyyMMdd
	 * @param DayVal
	 * @return
	 */
	public static String getDayAfter(String format, String DayVal) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(DayVal);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat(format).format(c.getTime());
		return dayAfter;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.dateFormat("20130203 090", "/", ":"));
	}
	
}