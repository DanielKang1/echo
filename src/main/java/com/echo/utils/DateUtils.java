package com.echo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	 public static int getDaysDiff(Date checkinDate,Date checkoutDate){
		  double quot=0;
		  long timeDiff=checkinDate.getTime()-checkoutDate.getTime();
		  quot=timeDiff/1000/60/60/24.0;
		  return (int) Math.ceil(quot);
	  }
	 
	 public static void main(String[] args) {
		    SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String checkin  = "2016-11-01 14:00:00";
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
