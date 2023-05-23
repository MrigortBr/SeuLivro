package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import service.BookService;


@WebServlet("/Pdf")
public class ControllerPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookService service = new BookService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") == null) {
			response.sendRedirect("/seulivro/Index");
		}else {
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "inline; filename=\"arquivo.pdf\"");
	        
			int idBook = Integer.parseInt(request.getParameter("id"));
			int idUser = 0;
			try {
				idUser = (int) request.getSession().getAttribute("id");
			} catch (Exception e) {
			}
					
	
			
			InputStream inputStream = service.getBookByIdPdf(idBook, idUser);
			
	        OutputStream outputStream = response.getOutputStream();
	        
	        byte[] buffer = new byte[4096];
	        int bytesRead;
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);
	        }
	        
	        // Fechar os streams
	        inputStream.close();
	        outputStream.close();

			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
