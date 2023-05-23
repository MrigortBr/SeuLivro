package controller;

import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import object.Book;
import service.BookService;
import service.Essentials;

/**
 * Servlet implementation class ControllerBook
 */
@WebServlet("/MyBooks")
@MultipartConfig
public class ControllerBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Essentials essentials = new Essentials();
	BookService service = new BookService();
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		Book book = null;
		InputStream pdf = null;
		byte[] cape = null;
		try {
			pdf = request.getPart("pdf").getInputStream();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			cape = essentials.tranformImageTo64(request.getPart("cape"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (request.getParameter("idBook") != null) {
			book = new Book(
					Integer.parseInt(request.getParameter("idBook")),
					request.getParameter("name"),
					cape,
					pdf,
					request.getParameter("author"),
					(int) request.getSession().getAttribute("id"),
					request.getParameter("description"));
		}else {
			book = new Book(
				request.getParameter("name"),
				cape,
				pdf,
				request.getParameter("author"),
				(int) request.getSession().getAttribute("id"),
				request.getParameter("description"));
		}					
		
		
		
		service.updateBook(book);
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("id") != null) {
			request.getRequestDispatcher("mybooks.jsp").forward(request, response);
		}else {
			response.sendRedirect("/seuLivro/Index");
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idBook = Integer.parseInt(request.getParameter("idBook"));
		int idPerson = (int) request.getSession().getAttribute("id");
		
		service.deleteBook(idBook, idPerson);
	}
	

}
