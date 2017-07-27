package com.josh.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
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
		
		response.setContentType("text/html");

		//authenticate
		if (!password.equals(pswdAttempt)) {
			writer.println("Access denied.");
			writer.println("<br><a href='index.html'>Back</a>");
			return;
		}
		
		String query = "select name, surname, email, occupation from myrecords";
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JOSHDATA", "root", "root");
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			request.setAttribute("records", rs);
			RequestDispatcher view = request.getRequestDispatcher("AllRecords.jsp");
			view.forward(request, response);
			
			if (!rs.next()) {
				writer.println("This database is empty.");
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					writer.println(rs.getString(1) + " "
					+ rs.getString(2) + "<br>"
					+ rs.getString(3) + "<br>"
					+ rs.getString(4) + "<br><br>");
				}
			}
			writer.println("\n<a href='index.html'>Back</a>");
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		writer.println("Not implemented yet.");
	}

}
