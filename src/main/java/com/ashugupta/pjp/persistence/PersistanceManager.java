package com.ashugupta.pjp.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ashugupta.pjp.models.Session;
import com.ashugupta.pjp.models.Transaction;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersistanceManager {

	String filePath;
	String fakeDataPath = "/Users/ashugupta/desktop/sapient/DateTimeCalculator/data.json";

	public PersistanceManager(String filePath) {
		this.filePath = filePath;
	}

	public PersistanceManager() {
		filePath = "/Users/ashugupta/desktop/sapient/DateTimeCalculator/history.json";
	}

	public void persistSession(Session session) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		JSONParser jsonParser = new JSONParser();
		try {
			boolean file = new File(filePath).createNewFile();
			JSONArray jsonArray;
			if (file) {
				jsonArray = new JSONArray();
			} else {
				jsonArray = (JSONArray) jsonParser.parse(new FileReader(filePath));
			}
			jsonArray.add(jsonParser.parse(mapper.writeValueAsString(session)));
			// jsonArray.add(JSONValue.toJSONString(transactions));

			try (FileWriter fileWriter = new FileWriter(filePath)) {
				fileWriter.write(jsonArray.toJSONString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw e;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public List<Session> readPreviousSessions(int count)
			throws JsonParseException, JsonMappingException, IOException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(filePath));
		List<Session> sessions = mapper.readValue(jsonArray.toJSONString(), new TypeReference<List<Session>>() {
		});
		if (count > sessions.size())
			return sessions;
		return sessions.subList(0, count - 1);
	}

	public void saveTransactions(List<Transaction> transactions) throws JsonProcessingException, IOException {
		FileWriter fileWriter = new FileWriter(fakeDataPath);
		ObjectMapper mapper = new ObjectMapper();
		String data = mapper.writeValueAsString(transactions);
		System.out.println(data);
		fileWriter.write(data);
		fileWriter.close();
	}

	public List<Transaction> readTransactions() throws FileNotFoundException, IOException, ParseException {
		ObjectMapper mapper = new ObjectMapper();
		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(fakeDataPath));
		List<Transaction> transactions = mapper.readValue(jsonArray.toJSONString(),
				new TypeReference<List<Transaction>>() {
				});
		return transactions;
	}

}
