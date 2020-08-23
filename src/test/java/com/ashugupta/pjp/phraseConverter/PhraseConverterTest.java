package com.ashugupta.pjp.phraseConverter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ashugupta.pjp.models.Date;

@RunWith(Parameterized.class)
public class PhraseConverterTest {

	private String phrase;
	private Date date;
	static LocalDate now = new LocalDate();

	@Parameters(name="{0}")
	public static Collection data() {
		return Arrays.asList(new Object[][] { 
			{ "today", new Date(now) },
			{ "tomorrow", new Date(now.plusDays(1)) },
			{ "day after tomorrow", new Date(now.plusDays(2)) },
			{ "yesterday", new Date(now.minusDays(1)) },
			{ "day before yesterday", new Date(now.minusDays(2)) },
			{ "last week", new Date(now.minusWeeks(1)) },
			{ "previous week", new Date(now.minusWeeks(1)) },
			{ "next week", new Date(now.plusWeeks(1)) },
			{ "next month", new Date(now.plusMonths(1)) },
			{ "next year", new Date(now.plusYears(1)) },
			{ "last month", new Date(now.minusMonths(1)) },
			{ "last year", new Date(now.minusYears(1)) },
			{ "month after", new Date(now.plusMonths(1)) },
			{ "month before", new Date(now.minusMonths(1)) },
			{ "6 days from now", new Date(now.plusDays(6)) },
			{ "6 weeks from now", new Date(now.plusWeeks(6)) },
			{ "6 months from now", new Date(now.plusMonths(6)) },
			{ "6 years from now", new Date(now.plusYears(6)) },
			{ "6 days earlier", new Date(now.minusDays(6)) },
			{ "6 weeks earlier", new Date(now.minusWeeks(6)) },
			{ "6 months earlier", new Date(now.minusMonths(6)) },
			{ "6 years earlier", new Date(now.minusYears(6)) },
		});
	}

	PhraseConverter phraseConverter;

	public PhraseConverterTest(String phrase, Date date) {
		super();
		this.phrase = phrase;
		this.date = date;
	}

	@Before
	public void setup() {
		phraseConverter = new PhraseConverter();
	}

	@Test
	public void shoudlConvertPhraseCorrectly() {
		Date date = phraseConverter.convertToDate(phrase);
		assertEquals(this.date, date);
	}
//
//	@Test
//	public void shouldConvertnWeeksFromNowCorrectly() {
//		Date date = phraseConverter.convertToDate("4 weeks from now");
//		assertEquals(new Date(new LocalDate().plusWeeks(4)), date);
//	}
//
//	@Test
//	public void shouldConvertnDaysFromNowCorrectly() {
//		Date date = phraseConverter.convertToDate("4 days from now");
//		assertEquals(new Date(new LocalDate().plusDays(4)), date);
//	}
}
