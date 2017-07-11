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

public class ADD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
    public ADD() {
        super();
    }
    
    protected void myRecords(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException, ClassNotFoundException, SQLException {
    	
    		String name = request.getParameter("name");
    		String surname = request.getParameter("surname");
    		String des = request.getParameter("des");
    		String gender = request.getParameter("gen");
    		String email = request.getParameter("email");
    		String password = request.getParameter("pswd");
    		String[] languages = request.getParameterValues("lang");
    		String langs = "";
    		for (int i = 0; i < languages.length; i++) {
    			langs += languages[i] + ", ";
    		}
    		
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JOSHDATA", "root", "root");
    		
    		String sql = "INSERT INTO MyRecords values (?, ?, ?, ?, ?, ?, ?)";
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setString(1, name);
    		ps.setString(2, surname);
    		ps.setString(3, des);
    		ps.setString(4, gender);
    		ps.setString(5, password); // Never ever ever store actual passwords like this
    		ps.setString(6, langs);
    		ps.setString(7, email);
    		ps.executeUpdate();
    		
    		PrintWriter writer = response.getWriter();
    		writer.println("The following data has been entered:\n\n"
    				+ "\nName: " + name
    				+ "\nSurname: " + surname
    				+ "\nOccupation: " + des
    				+ "\nGender: " + gender
    				+ "\nEmail: " + email
    				+ "\nPassword: (HIDDEN)"
    				+ "\nLanguages: " + langs
    				);

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			myRecords(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
