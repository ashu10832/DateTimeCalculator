package com.ashugupta.pjp.calculator;

import com.ashugupta.pjp.models.*;

public interface DateTimeCalculator {
	public Date addDates(Date first, Date second);
	
	public String addDates(String first, String second);
	
	public Date addDays(Date date, int days);
	
	public String addDays(String date, int days);
	
	public Date addWeeks(Date date, int weeks);
	
	public String addWeeks(String date, int weeks);
	
	public String getDay(Date date);
	
	public int getWeekNumber(Date date);
	
	public Date addMonths(Date date, int months);
	
	public Date addYears(Date date, int years);
	
	public Date subtractDays(Date date, int days);
	
	public Date subtractWeeks(Date date, int weeks);
	
	public Date subtractMonths(Date date, int months);
	
	public Date subtractYears(Date date, int years);
	
	public String subtractDays(String date, int days);
	
	public String subtractWeeks(String date, int weeks);
	
	public String subtractMonths(String date, int months);
	
	public String subtractYears(String date, int years);

	public String addMonths(String date, int duration);

	public String addYears(String date, int duration);
	
	
	
}
