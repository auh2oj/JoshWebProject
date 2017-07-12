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
 * Servlet implementation class updateInfo
 */
public class updateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateInfo() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String enteredPswd = request.getParameter("password");
		String info = request.getParameter("info");
		
		String query = "select password from myrecords where name=? and surname=?";
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JOSHDATA", "root", "root");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, surname);
			ResultSet rs = ps.executeQuery();
			
			if (!rs.next()) {
				writer.println("You do not have access to this feature. "
						+ "Please add yourself to the database first.");
			} else {
				rs.beforeFirst();
				String password = "";
				rs.next();
				password = rs.getString(1);
				if (!password.equals(enteredPswd)) {
					writer.println("Incorrect password.");
				} else {
					String dispatchURL = "";
					switch (info) {
					case "Email":
						dispatchURL = "UpdateEmail";
						break;
					case "Password":
						dispatchURL = "UpdatePassword";
						break;
					case "Occupation":
						dispatchURL = "UpdateOccupation";
						break;
					}
					writer.println(dispatchURL);
					RequestDispatcher view = request.getRequestDispatcher(dispatchURL);
					view.forward(request, response);
				}
			}
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

	private void updateOccupation() {
		// TODO Auto-generated method stub
		
	}

	private void updatePassword() {
		// TODO Auto-generated method stub
		
	}

	private void updateEmail() {
		// TODO Auto-generated method stub
		
	}

}
