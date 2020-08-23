package com.ashugupta.pjp.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.ashugupta.pjp.models.Session;
import com.ashugupta.pjp.models.Transaction;
import com.ashugupta.pjp.persistence.PersistanceManager;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class SessionManager {
	
	Session session;
	
	public SessionManager() {
		session = new Session();
	}
	
	public void addTransaction(Transaction transaction) {
		session.addTransaction(transaction);
	}

	public List<Transaction> getTransactions() {
		return session.getTransactions();
	}

	public void setTransactions(List<Transaction> transactions) {
		session.setTransactions(transactions);
	}
	
	public void persistToStorage() throws JsonProcessingException {
		PersistanceManager persistanceManager = new PersistanceManager();
		persistanceManager.persistSession(session);
	}

	public List<Session> getPreviousSessions(int count) throws JsonParseException, JsonMappingException, IOException, ParseException {
		PersistanceManager persistanceManager = new PersistanceManager();
		return persistanceManager.readPreviousSessions(count);
	}
}

