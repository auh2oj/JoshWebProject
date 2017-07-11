package com.josh.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		JDBC db = new JDBC();
		db.save();
	}

	void save() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JOSHDATA", "root", "root");
		PreparedStatement pst = con.prepareStatement("insert into emp values(123, 'josh', 'jg@gmail.com', 'jg@123456')");
		int i = pst.executeUpdate();
		
		if (i > 0) {
			System.out.println("data has been saved");
		} else {
			System.out.println("data not saved");
		}
	}
	
	void delete() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JOSHDATA", "root", "root");
		PreparedStatement pst = con.prepareStatement("delete from emp where id=1");
		int i = pst.executeUpdate();
		
		if (i > 0) {
			System.out.println("data has been deleted");
		} else {
			System.out.println("data not deleted");
		}
	}
	
}
