package com.ashugupta.pjp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ashugupta.pjp.calculator.DateTimeCalculator;
import com.ashugupta.pjp.calculator.DateTimeCalculatorImpl;

/**
 * Servlet implementation class SubtractServlet
 */
@WebServlet("/subtract")
public class SubtractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubtractServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		System.out.println(request.toString());
		DateTimeCalculator dateTimeCalculator = new DateTimeCalculatorImpl();
		String result = "";
		String date = request.getParameter("date");
		int duration = Integer.parseInt(request.getParameter("duration")) ;
		System.out.println(date);
		if(request.getParameter("operation").equalsIgnoreCase("day")) {
			result = dateTimeCalculator.subtractDays(date, duration);
		}else if (request.getParameter("operation").equalsIgnoreCase("week")) {
			result = dateTimeCalculator.subtractWeeks(date, duration);
		}
		else if (request.getParameter("operation").equalsIgnoreCase("month")) {
			result = dateTimeCalculator.subtractMonths(date, duration);
		}
		else if (request.getParameter("operation").equalsIgnoreCase("year")) {
			result = dateTimeCalculator.subtractYears(date, duration);
		}
		request.setAttribute("result",result);
		request.getRequestDispatcher("subtract.jsp").forward(request,response); 
	}
	}


