package com.ashugupta.pjp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Session implements Serializable {
	
	@JsonProperty("transactions")
	List<Transaction> transactions;

	public Session(List<Transaction> transactions) {
		super();
		this.transactions = transactions;
	}
	
	public Session() {
		transactions = new ArrayList<Transaction>();
	}

	@Override
	public String toString() {
		return "Session [transactions=" + transactions + "]";
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

}
