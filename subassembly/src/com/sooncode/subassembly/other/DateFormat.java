package com.sooncode.subassembly.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	private Date date;
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd hh:mm:ss";
	public static final String YYYY_MM_DD_FORMAT = "yyyy-MM-dd";
	public static final String HH_MM_SS_FORMAT = "hh:mm:ss";
	private static final SimpleDateFormat SDF = new SimpleDateFormat(DEFAULT_FORMAT);
	public DateFormat(String dateString) {
		try {
			this.date = SDF.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	public DateFormat(String dateString,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			this.date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	public DateFormat(Date date) {
		this.date = date ;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getDateString () {
		return SDF.format(this.date);
	}
	
	
	public String getDateString (String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(this.date);
	}
	
	
	 
}
