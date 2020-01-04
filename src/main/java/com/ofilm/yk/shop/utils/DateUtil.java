package com.ofilm.yk.shop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

	public static String dateTrans(Date date) {
			
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String format = dateFormat.format(date);
		
		return format;
		
	}

	public static Date dateFormat(LocalDateTime time){

		DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatTime = time.format(formatter);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		try {
			 date = dateFormat.parse(formatTime);
		}catch (ParseException e){
			e.printStackTrace();
		}
		return date;
	}
}
