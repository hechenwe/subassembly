package com.sooncode.subassembly.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class String2Date {

private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";	
	
/**
* 日期转换成字符串
* @param date 
* @return str
*/
public static String Date2String(Date date) {
  
   SimpleDateFormat format = new SimpleDateFormat(FORMAT);
   String str = format.format(date);
   return str;
} 

/**
* 字符串转换成日期
* @param str
* @return date
*/
public static Date string2Date(String string) {
  
   SimpleDateFormat format = new SimpleDateFormat(FORMAT);
   Date date = null;
   try {
    date = format.parse(string);
   } catch (ParseException e) {
    e.printStackTrace();
   }
   return date;
}

public static void main(String[] args) {
  
  System.out.println("String2Date.main()" +string2Date("2012-12-1 11:11:11") ); 
}

}