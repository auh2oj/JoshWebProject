package com.josh.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetNames
 */
public class GetNames extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String password = "admin";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNames() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String pswdAttempt = request.getParameter("password");
		if (!password.equals(pswdAttempt)) {
			writer.println("Access denied.");
			return;
		}
		
		writer.println("Not implemented yet.");
	}

}
