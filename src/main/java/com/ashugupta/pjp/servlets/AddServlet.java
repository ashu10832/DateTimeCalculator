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
 * Servlet implementation class AddServlet
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println(request.toString());
		DateTimeCalculator dateTimeCalculator = new DateTimeCalculatorImpl();
		String result = "";
		String date = request.getParameter("date");
		int duration = Integer.parseInt(request.getParameter("duration")) ;
		System.out.println(date);
		if(request.getParameter("operation").equalsIgnoreCase("day")) {
			result = dateTimeCalculator.addDays(date, duration);
		}else if (request.getParameter("operation").equalsIgnoreCase("week")) {
			result = dateTimeCalculator.addWeeks(date, duration);
		}
		else if (request.getParameter("operation").equalsIgnoreCase("month")) {
			result = dateTimeCalculator.addMonths(date, duration);
		}
		else if (request.getParameter("operation").equalsIgnoreCase("year")) {
			result = dateTimeCalculator.addYears(date, duration);
		}
		
		request.setAttribute("result",result);
		request.getRequestDispatcher("add.jsp").forward(request,response); 
		//response.getWriter().append(result);
	}


}
