package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import object.Book;
import object.Person;
import service.Essentials;
import service.PersonService;


@WebServlet("/profile")
@MultipartConfig
public class ControllerProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonService service = new PersonService();
	Essentials essentials = new Essentials();
			
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tagProfile = request.getParameter("tag");

		if (tagProfile == null) {
			if (request.getSession().getAttribute("id") != null) {
				Person person = service.getPersonForId((int) request.getSession().getAttribute("id"));
				request.setAttribute("person", person);
				request.getRequestDispatcher("myProfile.jsp").forward(request, response);
			}else {
				response.sendRedirect("/seuLivro/Index");
			}
		
		}else {
			Person person= service.getPersonForTag(tagProfile);
			if (person.getId() != 0) {
				request.setAttribute("person", person);
				ArrayList<Book> books = service.getBooksUserByTag(tagProfile);
				request.setAttribute("books", books);
				request.getRequestDispatcher("profile.jsp").forward(request, response);
			}else {
				response.sendRedirect("/seuLivro/Index");
			}

		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        
        if (request.getSession().getAttribute("id") != null) {
        	id = (int) request.getSession().getAttribute("id");
        }
        
		String name = (String) request.getParameter("name");
        String surname = request.getParameter("surname");
        String tag = request.getParameter("tag");
        String birth = request.getParameter("age");
        String email = request.getParameter("email");
        String bio = request.getParameter("bio");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        byte[] image = null;
        
        try {
        	image = essentials.tranformImageTo64(request.getPart("picture"));
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        Person person = new Person(id, name, surname, tag, birth, email, password, bio, image, newPassword);
        service.updatePerson(person);

	}

}
