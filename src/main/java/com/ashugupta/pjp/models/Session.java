package com.ashugupta.pjp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Session implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(mappedBy="session")
	@JsonProperty("transactions")
	Set<Transaction> transactions;

	public Session(Set<Transaction> transactions) {
		super();
		this.transactions = transactions;
	}
	
	public Session() {
		transactions = new HashSet<Transaction>();
	}

	@Override
	public String toString() {
		return "Session [transactions=" + transactions + "]";
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

}
