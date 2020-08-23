package com.ashugupta.pjp.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ashugupta.pjp.constants.Operation;
import com.ashugupta.pjp.models.Date;
import com.ashugupta.pjp.models.Transaction;
import com.ashugupta.pjp.persistence.PersistanceManager;
import com.fasterxml.jackson.core.JsonProcessingException;

public class DataGenerator {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		Transaction transaction = new Transaction();
		String []input = {"01-01-2020","10"};
		Operation operation = Operation.ADD_DAYS;
		transaction.setInput(input);
		transaction.setOperation(operation.toString());
		transaction.execute();
		System.out.println(transaction.getOutput());
		PersistanceManager persistanceManager = new PersistanceManager();
		persistanceManager.saveTransactions(generateTransactions(100000));
	}
	
	
	private static List<Transaction> generateTransactions(int count) {
		List<Transaction> transactions = new ArrayList();
		Random random = new Random();
		
		for(int i = 0;i<count;i++) {
			Transaction transaction = new Transaction();
			int operation = random.nextInt(10);		
			Operation op = Operation.values()[operation];
			long leftLimit = -1000000000L;
		    long rightLimit = 1000000000L;
		    long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
			if(op.toString().contains("ADD") || op.toString().contains("SUBTRACT")){
				String date = new Date(generatedLong).toString();
				String duration = String.valueOf(1 + random.nextInt(30));
				transaction.setInput(new String[] {date,duration});
				transaction.setOperation(op);
			}else {
				String date = new Date(generatedLong).toString();
				transaction.setInput(new String[] {date});
				transaction.setOperation(op);
			}
			System.out.println(transaction.toString());
			transactions.add(transaction);
		}
		return transactions;
	}

}
