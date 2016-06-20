package com.anand;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/ContactControllerServlet")
public class ContactControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDBUtil contactDBUtil;

	@Resource(name="jdbc/my_contacts")
	private DataSource dataSource;


	@Override
	public void init() throws ServletException {
		super.init();

		try{
			contactDBUtil = new ContactDBUtil(dataSource);
		}
		catch(Exception exc){
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("hidden");
		if ( command == null){
			command = "list";
		}
		try {
			switch(command){
			case "save":
				addContact(request,response);
				listContact(request,response);
				break;
			case "list":
				listContact(request, response);
				break;
			case "load":
				getDetail(request,response);
				break;
			case "update":
				updateDetail(request,response);
				listContact(request,response);
				break;
			case "delete":
				deleteContact(request,response);
				listContact(request,response);
				break;
			default:
				listContact(request, response);
			}

		} catch (Exception exe) {
			throw new ServletException(exe);
		}	

	}

	private void deleteContact(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String id = request.getParameter("contactId");	
		contactDBUtil.deleteContact(id, dataSource);
	}

	private void updateDetail(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String firstName= request.getParameter("firstName");
		String lastName= request.getParameter("lastName");
		String contactNumber= request.getParameter("contactNumber");
		String emailId= request.getParameter("emailId");
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		Contact contact = new Contact(id,firstName, lastName, contactNumber, emailId);
		request.setAttribute("contact", contact);

		contactDBUtil.updateContact(contact, dataSource);
		
	}

	private void getDetail(HttpServletRequest request,	HttpServletResponse response) throws Exception{
		String id = request.getParameter("contactId");
		Contact contact = contactDBUtil.getContact(id, dataSource);
		request.setAttribute("contact", contact);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Update-Contact.jsp");
		dispatcher.forward(request, response);
	}

	private void addContact(HttpServletRequest request, HttpServletResponse response)throws Exception {		
		String firstName= request.getParameter("firstName");
		String lastName= request.getParameter("lastName");
		String contactNumber= request.getParameter("contactNumber");
		String emailId= request.getParameter("emailId");
		Contact contact = new Contact(firstName, lastName, contactNumber, emailId);
		request.setAttribute("contact", contact);

		contactDBUtil.addContact(contact, dataSource);
	}

	private void listContact(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Contact> contacts = contactDBUtil.getContacts();		

		request.setAttribute("contacts", contacts);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-contacts.jsp");
		dispatcher.forward(request, response);


	}

}
