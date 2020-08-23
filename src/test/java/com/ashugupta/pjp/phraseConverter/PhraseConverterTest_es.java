package com.ashugupta.pjp.phraseConverter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ashugupta.pjp.models.Date;

@RunWith(Parameterized.class)
public class PhraseConverterTest_es {

	private String phrase;
	private Date date;
	static LocalDate now = new LocalDate();

	@Parameters
	public static Collection data() {
		return Arrays.asList(new Object[][] { 
			{ "hoy", new Date(now) },
			{ "manana", new Date(now.plusDays(1)) },
			{ "6 dias a partir de ahora", new Date(now.plusDays(6)) },
			{ "ayer", new Date(now.minusDays(1)) },			
		});
	}

	PhraseConverter phraseConverter;

	public PhraseConverterTest_es(String phrase, Date date) {
		super();
		this.phrase = phrase;
		this.date = date;
	}

	@Before
	public void setup() {
		phraseConverter = new PhraseConverter(new Locale("es"));
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
