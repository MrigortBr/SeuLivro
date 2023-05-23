package controller;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.PersonService;



@WebServlet("/Register")
@MultipartConfig

public class ControllerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PersonService service = new PersonService();
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String tag = request.getParameter("tag");
		String birth = request.getParameter("birth");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String bio = request.getParameter("bio");
    	Part filePart = request.getPart("file");
    	
    	JSONObject JSONresult = service.register(name, surname, tag, birth, email, password, bio, filePart);	
	}

}
