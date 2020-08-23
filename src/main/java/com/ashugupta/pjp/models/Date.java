package com.ashugupta.pjp.models;

import org.joda.time.LocalDate;
import org.joda.time.LocalDate.Property;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Date {
	
	private LocalDate localDate;
	DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MM-yyyy");
	
	public Date(LocalDate localDate) {
		this.localDate = localDate;
	}
	
	public Date(String date) {
		this.localDate = new LocalDate(date);
	}
	
	public Date(Long epoch) {
		this.localDate = new LocalDate(epoch);
	}

	public Date addWeeks(int weeks) {
		return new Date(localDate.plusWeeks(weeks)); 
	}
	
	public Date addDays(int days) {
		return new Date(localDate.plusDays(days)); 
	}
	
	public String getDay() {
		Property property = localDate.dayOfWeek();
		return property.getAsText();
	}

	@Override
	public String toString() {
		return localDate.toString(dtf);
	}

	@Override
	public boolean equals(Object obj) {
		Date date = (Date) obj;
		return localDate.toString(dtf).equals(date.toString());
	}

	public int getWeek() {
		return localDate.getWeekOfWeekyear();
	}

	public Date subtractDays(int days) {
		return new Date(localDate.minusDays(days));
	}
	
	public Date subtractWeeks(int weeks) {
		return new Date(localDate.minusWeeks(weeks));
	}

	public Date addMonths(int months) {
		return new Date(localDate.plusMonths(months)); 
	}

	public Date addYears(int years) {
		return new Date(localDate.plusYears(years)); 
	}

	public Date subtractMonths(int months) {
		return new Date(localDate.minusMonths(months));
	}

	public Date subtractYears(int years) {
		return new Date(localDate.minusYears(years));
	}

	
	
	
	
	
	
	
	
	

}
