package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import object.Book;
import object.Person;
import service.PersonService;

/**
 * Servlet implementation class ControllerIndex
 */
@WebServlet("/Index")
public class ControllerIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PersonService service = new PersonService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			ArrayList<Book> books = service.getBooksUser((int) request.getSession().getAttribute("id"));
			request.setAttribute("books", books);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
