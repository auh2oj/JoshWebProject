package com.josh.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateOccupation
 */
public class UpdateOccupation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOccupation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String occupation = request.getParameter("newoccupation");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		
		System.out.println(name+","+surname);
		
		String query = "update myrecords set occupation=? where name=? and surname=?";
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JOSHDATA", "root", "root");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, occupation);
			ps.setString(2, name);
			ps.setString(3, surname);
			int updated = ps.executeUpdate();
			System.out.println(ps);
			System.out.println(updated);
			if (updated == 0) {writer.println("Error updating database.");}
			else {writer.println("Database successfully updated.");}
			request.getRequestDispatcher("/index.html").include(request, response);
			
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
