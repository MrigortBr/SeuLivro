package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BookService;

@WebServlet("/rate")
public class ControllerRate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BookService service = new BookService();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int star = 0;
		int idPerson = 0;
		int idBook = 0;
		String rate = null;		
		if (request.getSession().getAttribute("id")!= null) {
			idPerson = (int) request.getSession().getAttribute("id");
			if (request.getParameter("rate") == null) {
				star = Integer.parseInt(request.getParameter("star"));
			}else {
				rate = request.getParameter("rate");
			}
			
			idBook = Integer.parseInt((String) request.getParameter("idBook"));
			
			service.rate(rate, star, idBook, idPerson);
		}else {
			response.sendRedirect("/seuLivro/Index");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
