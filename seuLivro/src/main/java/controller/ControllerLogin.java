package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.PersonService;

@WebServlet("/Login")
public class ControllerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PersonService service = new PersonService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean logged = service.checkLogin(request);
		if (logged) {
			response.sendRedirect("/seuLivro/Index");
		}else {
			request.getRequestDispatcher("login.html").forward(request, response);;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		response.getWriter().print(service.login(email, password, request));
	}

}
