package com.josh.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectOnEmail
 */
public class SelectOnEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String password = "admin";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOnEmail() {
        super();
    }
    
    protected void selectOnEmail(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException, ClassNotFoundException, SQLException{
    	
    		String email = request.getParameter("email");
    		String pswd = request.getParameter("password");
    		PrintWriter writer = response.getWriter();
    		//authenticate
    		if (!authenticate(pswd)) {
    			writer.println("Access denied.\n");
    		} else {
    			executeQuery(writer, email);
    		}

    }
    
    private boolean authenticate(String pswd) {
    		return pswd.equals(password);
    }
    
    private void executeQuery(PrintWriter writer, String email) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JOSHDATA", "root", "root");
	
		String sql = "select name, surname, email, occupation from myrecords where email = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		
		if (!rs.next()) {
			writer.println("The email you've entered does not exist.");
		}
		
		while (rs.next()) {
			String name = rs.getString(1) + " " + rs.getString(2);
			String e_mail = rs.getString(3);
			String occupation = rs.getString(4);
			writer.println(name + "\n" + e_mail + "\n" + occupation + "\n\n");
		}
		
		con.close();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			selectOnEmail(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
