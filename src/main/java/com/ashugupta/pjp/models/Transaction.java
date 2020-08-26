package com.ashugupta.pjp.models;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.ListType;

import com.ashugupta.pjp.calculator.DateTimeCalculatorImpl;
import com.ashugupta.pjp.constants.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="transaction")
@JsonAutoDetect
public class Transaction implements Serializable, Callable<String> {
	

	@Column(name="id")
	private int id;
		
	@Column(name="input1")
	@JsonProperty("input1")
	String input1;
	
	@Column(name = "input2")
	@JsonProperty("input2")
	String input2;
	
	@Column(name = "operation")
	@JsonProperty("operation")
	String operation;
	
	@Column(name = "output")
	@JsonProperty("output")
	String output;
	
	@ManyToOne
	@JoinColumn(name="session_id", nullable=false)
	Session session;

	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}


	public List<String> getInput() {
		if (input2 != null) {
			return List.of(input1,input2);
		}
		return List.of(input1);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public String getInput2() {
		return input2;
	}

	public void setInput2(String input2) {
		this.input2 = input2;
	}

	public void setInput(List<String> input) {
		this.input1 = input.get(0);
		if(input.size() > 1)
		this.input2 = input.get(1);
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", input1=" + input1 + ", input2=" + input2 + ", operation=" + operation
				+ ", output=" + output + "]";
	}

	public Transaction(List<String> input, Operation operation, String output) {
		super();
		this.input1 = input.get(0);
		if(input.size() > 1)
		this.input2 = input.get(1);
		this.operation = operation.toString();
		this.output = output;
	}
	
	public Transaction(String input[], Operation operation, String output) {
		super();
		this.input1 = input[0];
		if(input.length > 1)
		this.input2 = input[1];
		this.operation = operation.toString();
		this.output = output;
	}
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public String execute() {
		Operation op = Operation.valueOf(operation);
		DateTimeCalculatorImpl calculator = new DateTimeCalculatorImpl();
		switch (op) {
		case ADD_DAYS:
			output = calculator.addDays(getInput1(), Integer.parseInt(getInput2())).toString();
			return output;
		case ADD_WEEKS:
			output = calculator.addWeeks(getInput1(), Integer.parseInt(getInput2())).toString();
			return output;
		case ADD_MONTHS:
			output = calculator.addMonths(getInput1(), Integer.parseInt(getInput2())).toString();
			return output;
		case ADD_YEARS:
			output = calculator.addYears(getInput1(), Integer.parseInt(getInput2())).toString();
			return output;
		case SUBTRACT_DAYS:
			output = calculator.subtractDays(getInput1(), Integer.parseInt(getInput2())).toString();
			return output;
		case SUBTRACT_MONTHS:
			output = calculator.subtractWeeks(getInput1(), Integer.parseInt(getInput2())).toString();
			return output;
		case SUBTRACT_WEEKS:
			output = calculator.subtractMonths(getInput1(), Integer.parseInt(getInput2())).toString();
			return output;
		case SUBTRACT_YEARS:
			output = calculator.subtractYears(getInput1(), Integer.parseInt(getInput2())).toString();
			return output;
		case WEEK_OF_YEAR:
			output = Integer.toString(calculator.getWeekNumber(getInput1()));
			return output;
		case DAY_OF_WEEK:
			output = calculator.getDay(getInput1());
			return output;
		default:
			break;
		}
		return output;
		
	}

	public void setOperation(Operation op) {
		this.operation = op.toString();
	}

	@Override
	public String call() throws Exception {
		return execute();
	}

	@JsonIgnore
	public void setInput(String[] input) {
		this.input1 = input[0];
		if(input.length > 1)
		this.input2 = input[1];
	}
	
	

}
