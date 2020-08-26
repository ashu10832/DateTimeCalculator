package com.ashugupta.pjp.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ashugupta.pjp.constants.Operation;
import com.ashugupta.pjp.models.Session;
import com.ashugupta.pjp.models.Transaction;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class persistance {
	
	PersistanceManager persistanceManager;
	String filePath = "/Users/ashugupta/desktop/sapient/DateTimeCalculator/history-test.json";
	
	@Before
	public void setup() {
		persistanceManager = new PersistanceManager(filePath);
	}
	
	
	@Test
	public void shouldWriteDataToFile() throws JsonProcessingException {
		Set<Transaction> transactions = new HashSet<Transaction>();
		transactions.add(new Transaction(new String[] {"input1","input2"}, Operation.ADD_DAYS, "output"));
		transactions.add(new Transaction(new String[] {"input1"}, Operation.ADD_DAYS, "output"));
		
		Session session = new Session(transactions);
		
		persistanceManager.persistSession(session);
		
		JSONParser jsonParser = new JSONParser();
		try {
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(filePath));
			System.out.println(jsonArray.toJSONString());
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void shouldReadPreviousSessions() throws JsonParseException, JsonMappingException, IOException, ParseException {
		persistanceManager = new PersistanceManager();
		persistanceManager.readPreviousSessions(5);
	}
	
	@After
	public void teardown() {
		File file = new File(filePath);
		file.delete();
	}

}
