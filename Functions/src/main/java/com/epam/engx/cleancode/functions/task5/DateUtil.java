package com.epam.engx.cleancode.functions.task5;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public Date getIncrementedDate(Date date){
		Calendar calendar = getCalendarDate(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	public Date getDecrementedDate(Date date){
		Calendar calendar = getCalendarDate(date);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	private Calendar getCalendarDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

}
