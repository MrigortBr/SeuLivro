package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import object.Book;
import object.Person;
import service.BookService;
import service.PersonService;


@WebServlet("/Book")
public class ControllerBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BookService service = new BookService();
	PersonService servicePerson = new PersonService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") == null) {
			response.sendRedirect("/seuLivro/Index");
		}else {
			int idBook = Integer.parseInt(request.getParameter("id"));
			int idPerson = 0;
			if (request.getSession().getAttribute("id") != null) idPerson = (int) request.getSession().getAttribute("id");

			
			Book book = service.getBookById(idBook, idPerson);
			if (book == null) {
				response.sendRedirect("/seuLivro/Index");

			}else {
				request.setAttribute("book", book);
				
				request.getRequestDispatcher("book.jsp").forward(request, response);
			}

		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idBook = Integer.parseInt(request.getParameter("idBook"));
		int idPerson = 0;
		if (request.getSession().getAttribute("id") != null) {
			idPerson = (int) request.getSession().getAttribute("id");
		}else {
			response.sendRedirect("/seuLivro/Index");
		}
		
		service.unSelectBook(idBook, idPerson);
	}

}
