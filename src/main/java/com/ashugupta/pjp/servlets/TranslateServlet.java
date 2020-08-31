package com.ashugupta.pjp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ashugupta.pjp.models.Date;
import com.ashugupta.pjp.phraseConverter.PhraseConverter;

/**
 * Servlet implementation class transalateServlet
 */
@WebServlet("/translate")
public class TranslateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TranslateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String phrase = request.getParameter("phrase");
		PhraseConverter phraseConverter = new PhraseConverter();
		Date date = phraseConverter.convertToDate(phrase);
		
		request.setAttribute("result",date.toString());
		request.getRequestDispatcher("translate.jsp").forward(request,response); 
	}


}
