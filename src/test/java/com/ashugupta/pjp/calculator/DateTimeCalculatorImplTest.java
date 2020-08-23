package com.ashugupta.pjp.calculator;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ashugupta.pjp.models.Date;



public class DateTimeCalculatorImplTest {
	
	DateTimeCalculatorImpl dateTimeCalculator;
	
	@Before
	public void setup() {
		dateTimeCalculator = new DateTimeCalculatorImpl();
	}
	

	@Test
	public void shouldAddDaysToDateInObjectFormat() {
		Date zeroDate = new Date(new LocalDate(0));
		Date result = dateTimeCalculator.addDays(zeroDate, 2);
		assertEquals(new Date(172800000L), result);
	}
	
	@Test
	public void shouldAddDaysToDateInStringFormat() {
		Date date = new Date(0L);
		String result = dateTimeCalculator.addDays(date.toString(), 2);
		assertEquals(new Date(172800000L).toString(), result);
	}
	
	@Test
	public void shouldAddWeeksToDateInObjectFormat() {
		Date date = new Date(0L);
		Date result = dateTimeCalculator.addWeeks(date, 2);
		assertEquals( new Date(1209600000L), result);
	}
	
	@Ignore
	@Test
	public void shouldAddWeeksToDateInStringFormat() {
		String result = dateTimeCalculator.addWeeks("01-01-1970", 2);
		assertEquals(new DateTime(1209600000L).toString(), result);
	}
	
	@Test
	public void shouldGetTheDayOfTheWeek () {
		Date zeroDate = new Date(new LocalDate(0));
		String day = dateTimeCalculator.getDay(zeroDate);
		assertEquals("Thursday", day);
	}
	
	@Test
	public void shouldGetTheWeekNumber () {
		Date zeroDate = new Date(new LocalDate(0));
		int week = dateTimeCalculator.getWeekNumber(zeroDate);
		assertEquals(1, week);
	}

}
