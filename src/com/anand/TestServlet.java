package com.anand;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Define connection pool for resource injection
	@Resource(name="jdbc/my_contacts")
	private DataSource dataSource;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Step1: Setup PrintWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		//Step2: Get a connection to the database
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
		
		myConn = dataSource.getConnection();
			
		//Step3: Create a SQL statements
		String sql = "select * from contact";
		myStmt = myConn.createStatement();
		
		//Step4: Execute SQL statements
		myRs = myStmt.executeQuery(sql);
		
		//Step5: Process the result set
		while(myRs.next()){
			String email = myRs.getString("email");
			out.println(email);
		}
	
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
		
		
	}

	
	
	
}
