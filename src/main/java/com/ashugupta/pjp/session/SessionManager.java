package com.ashugupta.pjp.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.parser.ParseException;

import com.ashugupta.pjp.database.DBManager;
import com.ashugupta.pjp.models.Session;
import com.ashugupta.pjp.models.Transaction;
import com.ashugupta.pjp.persistence.PersistanceManager;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class SessionManager {
	
	Session session;
	DBManager dbManager;

	
	public SessionManager() {
		session = new Session();
		dbManager = new DBManager();
		dbManager.addSessionToDB(session);
	}
	
	public void addTransaction(Transaction transaction) {
		session.addTransaction(transaction);
		dbManager.addTransactionToDB(transaction);
	}

	public Set<Transaction> getTransactions() {
		return session.getTransactions();
	}

	public void setTransactions(Set<Transaction> transactions) {
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

	public Session getSession() {
		return session;
	}
}

