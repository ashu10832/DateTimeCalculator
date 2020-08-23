package com.ashugupta.pjp.models;

import java.io.Serializable;

import com.ashugupta.pjp.calculator.DateTimeCalculatorImpl;
import com.ashugupta.pjp.constants.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Transaction implements Serializable {
	
	@JsonProperty("input")
	String input[];
	
	@JsonProperty("operation")
	String operation;
	
	@JsonProperty("output")
	String output;

	public String[] getInput() {
		return input;
	}

	public void setInput(String[] input) {
		this.input = input;
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
		return "Operation [input=" + input + ", operation=" + operation + ", output=" + output + "]";
	}

	public Transaction(String[] input, Operation operation, String output) {
		super();
		this.input = input;
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
			output = calculator.addDays(input[0], Integer.parseInt(input[1])).toString();
			return output;
		case ADD_WEEKS:
			output = calculator.addWeeks(input[0], Integer.parseInt(input[1])).toString();
			return output;
		case ADD_MONTHS:
			output = calculator.addMonths(input[0], Integer.parseInt(input[1])).toString();
			return output;
		case ADD_YEARS:
			output = calculator.addYears(input[0], Integer.parseInt(input[1])).toString();
			return output;
		case SUBTRACT_DAYS:
			output = calculator.subtractDays(input[0], Integer.parseInt(input[1])).toString();
			return output;
		case SUBTRACT_MONTHS:
			output = calculator.subtractWeeks(input[0], Integer.parseInt(input[1])).toString();
			return output;
		case SUBTRACT_WEEKS:
			output = calculator.subtractMonths(input[0], Integer.parseInt(input[1])).toString();
			return output;
		case SUBTRACT_YEARS:
			output = calculator.subtractYears(input[0], Integer.parseInt(input[1])).toString();
			return output;
		case WEEK_OF_YEAR:
			output = Integer.toString(calculator.getWeekNumber(input[0]));
			return output;
		case DAY_OF_WEEK:
			output = calculator.getDay(input[0]);
			return output;
		default:
			break;
		}
		return output;
		
	}

	public void setOperation(Operation op) {
		this.operation = op.toString();
	}
	
	

}
