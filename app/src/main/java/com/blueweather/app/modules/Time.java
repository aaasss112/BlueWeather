package com.blueweather.app.modules;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time {

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getNowYMDHMSTime(){
		
		
		SimpleDateFormat mDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = mDateFormat.format(new Date());
		return date;
	}
	/**
	 * MM-dd HH:mm:ss
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getNowMDHMSTime(){
		
		SimpleDateFormat mDateFormat = new SimpleDateFormat(
				"MM-dd HH:mm:ss");
		String date = mDateFormat.format(new Date());
		return date;
	}
	/**
	 * MM-dd
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getNowYMD(){

		SimpleDateFormat mDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		String date = mDateFormat.format(new Date());
		return date;
	}

	/**
	 * yyyy-MM-dd
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getYMD(Date date){

		SimpleDateFormat mDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		String dateS = mDateFormat.format(date);
		return dateS;
	}

	/**
	 * 将日期转化成星期
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getWeek(String dateStr) throws ParseException {
		SimpleDateFormat mDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		Date date =mDateFormat.parse(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int number = calendar.get(Calendar.DAY_OF_WEEK);//星期表示1-7，是从星期日开始，
		String [] str = {"","周日","周一","周二","周三","周四","周五","周六",};
		return str[number];
	}
}
