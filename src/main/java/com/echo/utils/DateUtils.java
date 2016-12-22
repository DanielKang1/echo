package com.echo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	 public static int getDaysDiff(Date checkinDate,Date checkoutDate){
		  double quot=0;
		  long timeDiff=checkinDate.getTime()-checkoutDate.getTime();
		  quot=timeDiff/1000/60/60/24.0;
		  return (int) Math.ceil(quot);
	  }
	 
	 public static String getCurrentDate(){
		 SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 return fmt.format(new Date());
	 }
	 
	 public static Date getDate(String str){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = null ;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return date;
	 }
	 
	 public static Date getCorrectiveCheckinDate(Date checkindate){
	    Calendar calendar = Calendar.getInstance();
		calendar.setTime(checkindate);
		calendar.add(Calendar.HOUR, 14);  
		return calendar.getTime();
	 }
	 
	 public static Date getCorrectiveCheckoutDate(Date checkoutdate){
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(checkoutdate);
		calendar.add(Calendar.HOUR, 12);  
		return calendar.getTime();
     }
	 
	 public static Date getCorrectiveLatestDate(Date checkindate){
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(checkindate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return  calendar.getTime(); 
     }
	 
	 public static boolean apartOver6hours(Date latestDate){
		 long nd = 1000 * 60 * 60;
		 long diff = (latestDate.getTime() - new Date().getTime());
		 if((diff / nd) <= 6){
			 return true;
		 }
		 return false;
	 }
	 
	 
	 public static void main(String[] args) {
		    SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String checkin  = "2016-12-19 21:00:00";
			String checkout  = "2016-11-02 12:00:00";
			Date checkindate = null;
			Date checkoutdate = null;
			try {
				 checkindate = myFmt2.parse(checkin);
				 checkoutdate = myFmt2.parse(checkout);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println(getDaysDiff(checkoutdate, checkindate));
			
	}

}
