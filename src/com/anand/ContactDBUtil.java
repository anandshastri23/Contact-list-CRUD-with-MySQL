package com.anand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ContactDBUtil {

	private DataSource dataSource;

	public ContactDBUtil(DataSource theDataSource){
		this.dataSource = theDataSource;
	}
	public List<Contact> getContacts() throws Exception{

		List<Contact> Contacts = new ArrayList<Contact>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRS = null;
		try {
			myConn = dataSource.getConnection();

			String sql = "select * from contact order by first_name";

			myStmt = myConn.createStatement();

			myRS = myStmt.executeQuery(sql);

			while(myRS.next()){
				Contact contact = new Contact(myRS.getInt("id"),myRS.getString("first_name"),myRS.getString("last_name"),myRS.getString("contact_number"),myRS.getString("email"));
				Contacts.add(contact);
			}

		}
		catch (Exception exc){
			exc.printStackTrace();
		}
		finally{
			close(myConn,myStmt,myRS);
		}

		return Contacts;
	}

	public void addContact(Contact contact, DataSource theDataSource)throws Exception{

		Connection myConn = null;
		PreparedStatement myStmt = null;
		try{
			myConn = dataSource.getConnection();

			String sql = "insert into contact (first_name, last_name, contact_number, email)"
					+ "values (?,?,?,?)";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, contact.getFirstName());
			myStmt.setString(2, contact.getLastName());
			myStmt.setString(3, contact.getContactNumber());
			myStmt.setString(4, contact.getEmailId());

			myStmt.execute();
		}
		catch(Exception exe){
			exe.printStackTrace();	
		}
		finally{
			close(myConn,myStmt, null);
		}

	}

	public Contact getContact(String parameter, DataSource dataSource) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRS = null;
		System.out.println("parameter is not null"+parameter);
		int contactId = Integer.parseInt(parameter);
		//int contactId = Integer.getInteger(parameter);


		try {
			myConn = dataSource.getConnection();

			String sql = "select * from contact where id=? ";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1,contactId);

			myRS = myStmt.executeQuery();
			if(myRS.next()){
				Contact contact = new Contact(myRS.getInt("id"),myRS.getString("first_name"),myRS.getString("last_name"),myRS.getString("contact_number"),myRS.getString("email"));
				return contact;
			}
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
		finally{
			close(myConn,myStmt,myRS);
		}
		return null;
	}

	public void updateContact(Contact contact, DataSource dataSource) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try{
			myConn = dataSource.getConnection();

			String sql = "update contact set first_name=?,"
											+ "last_name=?, "
											+ "contact_number=?, "
											+ "email=? "
											+ "where id=?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, contact.getFirstName());
			myStmt.setString(2, contact.getLastName());
			myStmt.setString(3, contact.getContactNumber());
			myStmt.setString(4, contact.getEmailId());
			myStmt.setInt(5, contact.getId());

			myStmt.execute();
		}
		catch(Exception exe){
			exe.printStackTrace();	
		}
		finally{
			close(myConn,myStmt, null);
		}
	}
	public void deleteContact(String id, DataSource dataSource) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		int contactId = Integer.parseInt(id);
		try{
			myConn = dataSource.getConnection();

			String sql = "delete from contact where id=?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, contactId);

			myStmt.execute();
		}
		catch(Exception exe){
			exe.printStackTrace();	
		}
		finally{
			close(myConn,myStmt, null);
		}
		
	}
	private void close(Connection myConn, Statement myStmt, ResultSet myRS) {
		try{
			if (myRS != null){
				myRS.close();
			}
			if (myStmt != null){
				myStmt.close();
			}
			if (myConn != null){
				myConn.close(); //doesn't really close the connection, it will be made available for others
			}

		}
		catch(Exception exc){
			exc.printStackTrace();
		}

	}
	



}


