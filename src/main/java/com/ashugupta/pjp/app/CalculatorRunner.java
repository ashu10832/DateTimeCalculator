package com.ashugupta.pjp.app;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.parser.ParseException;

import com.ashugupta.pjp.calculator.DateTimeCalculatorImpl;
import com.ashugupta.pjp.constants.Operation;
import com.ashugupta.pjp.models.Date;
import com.ashugupta.pjp.models.Session;
import com.ashugupta.pjp.models.Transaction;
import com.ashugupta.pjp.persistence.PersistanceManager;
import com.ashugupta.pjp.phraseConverter.PhraseConverter;
import com.ashugupta.pjp.session.SessionManager;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class CalculatorRunner {
	
	private static Locale locale;
	private static SessionManager sessionManager;

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, ParseException {
		int option = 1;
		locale = new Locale("en");
		
		sessionManager = new SessionManager();

		DateTimeCalculatorImpl calculator = new DateTimeCalculatorImpl();

		while (option != 0) {
			displayMainMenu();

			Scanner sc = new Scanner(System.in);
			option = sc.nextInt();

			int periodOption;
			String date;
			int duration;
			String output;
			switch (option) {
			case 1: {
				displayPeriods();
				periodOption = sc.nextInt();

				switch (periodOption) {
				case 1:
					date = inputDate();
					duration = inputDuration();
					output = calculator.addDays(parseDate(date), duration).toString();
					System.out.println(output);
					addToSession(new String[] {date,Integer.toString(duration)},output,Operation.ADD_WEEKS);
					break;
				case 2:
					date = inputDate();
					duration = inputDuration();
					output = calculator.addWeeks(parseDate(date), duration).toString();
					System.out.println(output);
					addToSession(new String[] {date,Integer.toString(duration)},output,Operation.ADD_WEEKS);
					break;
				case 3:
					date = inputDate();
					duration = inputDuration();
					output = calculator.addMonths(parseDate(date), duration).toString();
					System.out.println(output);
					addToSession(new String[] {date,Integer.toString(duration)},output,Operation.ADD_MONTHS);

					break;
				case 4:
					date = inputDate();
					duration = inputDuration();
					output = calculator.addYears(parseDate(date), duration).toString();
					System.out.println(output);
					addToSession(new String[] {date,Integer.toString(duration)},output,Operation.ADD_YEARS);
					break;
				case 0:
					break;
				default:
					break;
				}
				break;
			}
			case 2: {
				displayPeriods();
				periodOption = sc.nextInt();
				switch (periodOption) {
				case 1:
					date = inputDate();
					duration = inputDuration();
					output = calculator.subtractDays(parseDate(date), duration).toString();
					System.out.println(output);
					addToSession(new String[] {date,Integer.toString(duration)},output,Operation.SUBTRACT_DAYS);					
					break;
				case 2:
					date = inputDate();
					duration = inputDuration();
					output = calculator.subtractWeeks(parseDate(date), duration).toString();
					System.out.println(output);
					addToSession(new String[] {date,Integer.toString(duration)},output,Operation.SUBTRACT_WEEKS);					
					break;
				case 3:
					date = inputDate();
					duration = inputDuration();
					output = calculator.subtractMonths(parseDate(date), duration).toString();
					System.out.println(output);
					addToSession(new String[] {date,Integer.toString(duration)},output,Operation.SUBTRACT_MONTHS);					
					break;
				case 4:
					date = inputDate();
					duration = inputDuration();
					output = calculator.subtractYears(parseDate(date), duration).toString();
					System.out.println(output);
					addToSession(new String[] {date,Integer.toString(duration)},output,Operation.SUBTRACT_YEARS);					
					break;
				case 0:
					break;
				default:
					break;
				}
				break;
			}
			case 3: {
				date = inputDate();
				output = calculator.getDay( parseDate(date));
				System.out.println(output);
				addToSession(new String[] {date},output,Operation.DAY_OF_WEEK);					
				break;
			}
			case 4: {
				date = inputDate();
				output = Integer.toString(calculator.getWeekNumber(parseDate(date)));
				System.out.println(output);
				addToSession(new String[] {date},output,Operation.WEEK_OF_YEAR);		
				break;
			}
			case 5: {
				String phrase = inputPhrase();
				output = convertPhrase(phrase).toString();
				System.out.println(output);
				addToSession(new String[] {phrase},output,Operation.PHRASE_CONVERT);		
				break;
			}
			case 6: {
				displayLanguages();
				int langOpt = inputLanguage();
				if(langOpt == 1) {
					locale = new Locale("en");
				}else if(langOpt == 2) {
					locale = new Locale("es");
				}
				break;
			}
			case 7: {
				List<Session> sessions = sessionManager.getPreviousSessions(100);
				printSessions(sessions);
				break;
			}
			case 8: {
				PersistanceManager persistanceManager = new PersistanceManager();
				List<Transaction> transactions =  persistanceManager.readTransactions();
				for (Transaction transaction : transactions) {
					transaction.execute();
					sessionManager.addTransaction(transaction);
					System.out.println(transaction.getOutput());
				}
				break;
			}
			case 0:{
				try {
					sessionManager.persistToStorage();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
			default:
				break;
			}
		}

	}
	
	private static void printSessions(List<Session> sessions) {
		for (Session session : sessions) {
			System.out.println(session.toString());
		}
		
		
	}

	private static void addToSession(String [] input, String output, Operation operation) {
		sessionManager.addTransaction(new Transaction(input,operation,output));
	}

	private static void displayMainMenu() {
		System.out.println("Welcome to date-time calculator...");
		System.out.println("1. Add");
		System.out.println("2. Subtract");
		System.out.println("3. Day of the week");
		System.out.println("4. Week of the year");
		System.out.println("5. Translate Phrases");
		System.out.println("6. Switch Languages");
		System.out.println("7. View last 100 sessions:");
		System.out.println("8. Execute Bulk Transactions in data.json:");
		System.out.println("0. Exit");
		System.out.println("Choose : ");
	}

	private static void displayPeriods() {
		System.out.println("1. Day");
		System.out.println("2. Week");
		System.out.println("3. Month");
		System.out.println("4. Year");
	}
	
	private static void displayLanguages() {
		System.out.println("1. English");
		System.out.println("2. Spanish");
	}
	
	private static int inputLanguage() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose Language: ");
		return sc.nextInt();
	}

	private static String inputDate() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter date in format DD-MM-YYYY");
		return sc.nextLine();
	}

	private static String inputPhrase() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the phrase: ");
		return sc.nextLine();
	}

	private static int inputDuration() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter duration");
		return sc.nextInt();
	}

	private static Date parseDate(String date) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MM-yyyy");
		return new Date(dtf.parseLocalDate(date));
	}

	private static Date convertPhrase(String phrase) {
		PhraseConverter phraseConverter = new PhraseConverter(locale);
		return phraseConverter.convertToDate(phrase);
	}
}
