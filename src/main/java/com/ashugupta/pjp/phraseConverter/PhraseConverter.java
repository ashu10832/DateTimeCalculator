package com.ashugupta.pjp.phraseConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.joda.time.LocalDate;

import com.ashugupta.pjp.calculator.DateTimeCalculatorImpl;
import com.ashugupta.pjp.models.Date;

public class PhraseConverter {

	private Locale locale;
	private ResourceBundle bundle;

	private String TODAY;
	private String TOMORROW;
	private String DAY;
	private String AFTER;
	private String YESTERDAY;
	private String BEFORE;
	private String LAST;
	private String WEEK;
	private String PREVIOUS;
	private String NEXT;
	private String MONTH;
	private String YEAR;
	private String FROM;
	private String NOW;
	private String YEARS;
	private String MONTHS;
	private String WEEKS;
	private String DAYS;
	private String EARLIER;

	public PhraseConverter(Locale locale) {
		this.locale = locale;
		bundle = ResourceBundle.getBundle("strings", locale);
		initialiseStrings();
	}

	public PhraseConverter() {
		this.locale = new Locale("en");
		bundle = ResourceBundle.getBundle("strings", locale);
		initialiseStrings();

	}

	private void initialiseStrings () {
		TODAY = bundle.getString("today");
		TOMORROW=bundle.getString("tomorrow");
		TODAY=bundle.getString("today");
		DAY=bundle.getString("day");
		AFTER=bundle.getString("after");
		YESTERDAY=bundle.getString("yesterday");
		BEFORE=bundle.getString("before");
		LAST=bundle.getString("last");
		WEEK=bundle.getString("week");
		PREVIOUS=bundle.getString("previous");
		NEXT=bundle.getString("next");
		MONTH=bundle.getString("month");
		YEAR=bundle.getString("year");
		FROM=bundle.getString("from");
		NOW=bundle.getString("now");
		YEARS=bundle.getString("years");
		MONTHS=bundle.getString("months");
		WEEKS=bundle.getString("weeks");
		DAYS=bundle.getString("days");
		EARLIER=bundle.getString("earlier");
	}

	public Date convertToDate(String phrase) {

		String[] tokens = phrase.toLowerCase().split(" ");
		List<String> list = Arrays.asList(tokens);

		DateTimeCalculatorImpl calculator = new DateTimeCalculatorImpl();
		Date date = new Date(new LocalDate());

		if (list.contains(TODAY) && list.size() == 1) {
			return date;
		}
		if (list.contains(TOMORROW) && list.size() == 1) {
			return calculator.addDays(date, 1);
		}
		if (list.contains(DAY) &&list.contains(AFTER) && list.contains(TOMORROW) ) {
			return calculator.addDays(date, 2);
		}
		if (list.contains(YESTERDAY) && list.size() == 1) {
			return calculator.subtractDays(date, 1);
		}
		if (list.contains(DAY) &&list.contains(BEFORE) && list.contains(YESTERDAY) ) {
			return calculator.subtractDays(date, 2);
		}
		if( list.contains(NEXT)) {
			if(list.contains(WEEK)) {
				return calculator.addWeeks(date, 1);
			}
			if(list.contains(MONTH)) {
				return calculator.addMonths(date, 1);
			}
			if(list.contains(YEAR)) {
				return calculator.addYears(date, 1);
			}
		}
		
		if (list.contains(FROM) && list.contains(NOW)) {
			if (list.contains(WEEKS) || list.contains(WEEK)) {
				int amount = findNumber(list);
				if (amount != -1)
					return calculator.addWeeks(date, amount);
			}
			if (list.contains(DAYS) || list.contains(DAY)) {
				int amount = findNumber(list);
				if (amount != -1)
					return calculator.addDays(date, amount);
			}
			if (list.contains(YEARS) || list.contains(YEAR)) {
				int amount = findNumber(list);
				if (amount != -1)
					return calculator.addYears(date, amount);
			}
			if (list.contains(MONTHS) || list.contains(MONTH)) {
				int amount = findNumber(list);
				if (amount != -1)
					return calculator.addMonths(date, amount);
			}
		}
		if (list.contains(EARLIER)) {
			if (list.contains(WEEKS) || list.contains(WEEK)) {
				int amount = findNumber(list);
				if (amount != -1)
					return calculator.subtractWeeks(date, amount);
			}
			if (list.contains(DAYS) || list.contains(DAY)) {
				int amount = findNumber(list);
				if (amount != -1)
					return calculator.subtractDays(date, amount);
			}
			if (list.contains(YEARS) || list.contains(YEAR)) {
				int amount = findNumber(list);
				if (amount != -1)
					return calculator.subtractYears(date, amount);
			}
			if (list.contains(MONTHS) || list.contains(MONTH)) {
				int amount = findNumber(list);
				if (amount != -1)
					return calculator.subtractMonths(date, amount);
			}
		}
		if (list.contains(PREVIOUS)) {
			if (list.contains(DAYS) || list.contains(DAY)) {
				return calculator.subtractDays(date, 1);
			}
			if (list.contains(WEEKS) || list.contains(WEEK)) {
				return calculator.subtractWeeks(date, 1);
			}
			if (list.contains(MONTHS) || list.contains(MONTH)) {
				return calculator.subtractMonths(date, 1);
			}
			if (list.contains(YEAR) || list.contains(YEARS)) {
				return calculator.subtractYears(date, 1);
			}
		}
		if (list.contains(LAST)) {
			if (list.contains(DAYS) || list.contains(DAY)) {
				return calculator.subtractDays(date, 1);
			}
			if (list.contains(WEEKS) || list.contains(WEEK)) {
				return calculator.subtractWeeks(date, 1);
			}
			if (list.contains(MONTHS) || list.contains(MONTH)) {
				return calculator.subtractMonths(date, 1);
			}
			if (list.contains(YEAR) || list.contains(YEARS)) {
				return calculator.subtractYears(date, 1);
			}
		}
		if (list.contains(AFTER)) {
			if (list.contains(WEEKS) || list.contains(WEEK)) {
				int amount = findNumber(list);
				if (amount == -1)
					amount = 1;
				return calculator.addWeeks(date, amount);
			}
			if (list.contains(DAYS) || list.contains(DAY)) {
				int amount = findNumber(list);
				if (amount == -1)
					amount = 1;
				return calculator.addDays(date, amount);
			}
			if (list.contains(MONTHS) || list.contains(MONTH)) {
				int amount = findNumber(list);
				if (amount == -1)
					amount = 1;
				return calculator.addMonths(date, amount);
			}
			if (list.contains(YEARS) || list.contains(YEAR)) {
				int amount = findNumber(list);
				if (amount == -1)
					amount = 1;
				return calculator.addYears(date, amount);
			}
		}
		if (list.contains(BEFORE)) {
			if (list.contains(WEEKS) || list.contains(WEEK)) {
				int amount = findNumber(list);
				if (amount == -1)
					amount = 1;
				return calculator.subtractWeeks(date, amount);
			}
			if (list.contains(DAYS) || list.contains(DAY)) {
				int amount = findNumber(list);
				if (amount == -1)
					amount = 1;
				return calculator.subtractDays(date, amount);
			}
			if (list.contains(MONTHS) || list.contains(MONTH)) {
				int amount = findNumber(list);
				if (amount == -1)
					amount = 1;
				return calculator.subtractMonths(date, amount);
			}
			if (list.contains(YEARS) || list.contains(YEAR)) {
				int amount = findNumber(list);
				if (amount == -1)
					amount = 1;
				return calculator.subtractYears(date, amount);
			}
		}

		
		return null;
	}

	private int findNumber(List<String> list) {
		String num = list.stream().filter(word -> word.chars().allMatch(Character::isDigit)).findAny().orElse(null);
		if (num != null) {
			return Integer.parseInt(num);
		}
		return -1;
	}
}
