package com.ashugupta.pjp.calculator;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.ashugupta.pjp.models.*;


public class DateTimeCalculatorImpl implements DateTimeCalculator {
	
	@Override
	public Date addDays(Date date, int days) {
		return date.addDays(days);
	}

	@Override
	public String addDays(String date, int days) {
		return addDays(parseDate(date), days).toString();
	}

	@Override
	public Date addWeeks(Date date, int weeks) {
		return date.addWeeks(weeks);
	}

	@Override
	public String addWeeks(String date, int weeks) {
		return addWeeks(parseDate(date), weeks).toString();
	}

	@Override
	public Date addDates(Date first, Date second) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addDates(String first, String second) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDay(Date date) {
		return date.getDay();
	}

	@Override
	public int getWeekNumber(Date date) {
		return date.getWeek();
	}

	@Override
	public Date subtractDays(Date date, int days) {
		return date.subtractDays(days);
	}
	
	@Override
	public Date subtractWeeks(Date date, int weeks) {
		return date.subtractWeeks(weeks);
	}
	
	@Override
	public Date addMonths(Date date, int months) {
		return date.addMonths(months);
	}

	@Override
	public Date addYears(Date date, int years) {
		return date.addYears(years);
	}

	@Override
	public Date subtractMonths(Date date, int months) {
		return date.subtractMonths(months);
	}

	@Override
	public Date subtractYears(Date date, int years) {
		return date.subtractYears(years);
	}
	
	private Date parseDate(String date) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MM-yyyy");
		return new Date(dtf.parseLocalDate(date));
	}

	public String addMonths(String date, int months) {
		return addMonths(parseDate(date), months).toString();
	}

	public String addYears(String date, int years) {
		return addYears(parseDate(date), years).toString();
	}

	@Override
	public String subtractDays(String date, int days) {
		return subtractDays(parseDate(date), days).toString();
	}

	@Override
	public String subtractWeeks(String date, int weeks) {
		return subtractWeeks(parseDate(date), weeks).toString();
	}

	@Override
	public String subtractMonths(String date, int months) {
		return subtractMonths(parseDate(date), months).toString();
	}

	@Override
	public String subtractYears(String date, int years) {
		return subtractYears(parseDate(date), years).toString();
	}

	public int getWeekNumber(String date) {
		return getWeekNumber(parseDate(date));
	}

	public String getDay(String date) {
		return getDay(parseDate(date));
	}
	
}
