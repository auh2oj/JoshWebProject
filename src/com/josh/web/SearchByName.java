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
 * Servlet implementation class SearchByName
 */
public class SearchByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByName() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = "select name, surname, email, occupation from myrecords where name=?";
		Connection con = null;
		PrintWriter writer = response.getWriter();
		
		String name = request.getParameter("name");
		
		try {
			con = establishConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if (!rs.next()) {
				writer.println("Person does not exist in the database.");
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					writer.println(rs.getString(1) + ";  "
					+ rs.getString(2) + ";  "
					+ rs.getString(3) + ";  "
					+ rs.getString(4));
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

		
		
		
		
		
		
		
		
		
//		String name = request.getParameter("name");
//		PrintWriter writer = response.getWriter();
//		writer.println("Not implemented");
//		Connection con = null;
//		try {
//			con = establishConnection();
//			String sql = "select name, surname, email, occupation from myrecords where name = ?";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, name);
//			ResultSet rs = ps.executeQuery();
//			
//			if (!rs.next()) {
//				writer.println("Name doesn't exist");
//			}
//			
//			while (rs.next()) {
//				writer.println(rs.getString(1) + " "
//						+ rs.getString(2) + " "
//						+ rs.getString(3) + " "
//						+ rs.getString(4));
//			}
//			
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				if (con != null && !con.isClosed()) {
//					con.close();
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.err.println("Failed to close connection");
//			}
//		}

		


	}
	
	private Connection establishConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JOSHDATA", "root", "root");
		return con;
	}

}
