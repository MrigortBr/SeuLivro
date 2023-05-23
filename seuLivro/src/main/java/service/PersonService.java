package service;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.json.JSONObject;

import com.mysql.cj.jdbc.CallableStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.DAO;
import object.Book;
import object.Person;

public class PersonService {
	DAO dao = new DAO();
	
	public Person getPersonForId(int id){
		Connection con = dao.connect();
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call select_person(?)}");
			cs.setInt(1, id);
			cs.execute();
		    ResultSet rs = cs.getResultSet();
			
			int idUser = id;
			String name = "";
			String surname = "";
			String tag = "";
			String birth = "";
			String email = "";
			String password = "";
			String bio = "";
			byte[] image = null;
		    
			if (rs.next()) {
				idUser = rs.getInt("id");
				name = rs.getString("name");
				surname = rs.getString("surname");
				tag = rs.getString("tag");
				birth = rs.getString("birth");
				email = rs.getString("email");
				password = rs.getString("password");
				bio = rs.getString("bio");
				image = rs.getBytes("image");
		    };
		    dao.close(con);
		    return new Person(idUser, name, surname, tag, birth, email, password, bio, image);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Person getPersonForTag(String tagUser){
		Connection con = dao.connect();
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call select_person_tag(?)}");
			cs.setString(1, tagUser);
			cs.execute();
		    ResultSet rs = cs.getResultSet();
			
			int idUser = 0;
			String name = "";
			String surname = "";
			String tag = "";
			String birth = "";
			String email = "";
			String password = "";
			String bio = "";
			byte[] image = null;
		    
			if (rs.next()) {
				idUser = rs.getInt("id");
				name = rs.getString("name");
				surname = rs.getString("surname");
				tag = rs.getString("tag");
				birth = rs.getString("birth");
				email = rs.getString("email");
				password = rs.getString("password");
				bio = rs.getString("bio");
				image = rs.getBytes("image");
		    };
		    dao.close(con);
		    return new Person(idUser, name, surname, tag, birth, email, password, bio, image);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public JSONObject login(String email, String password, HttpServletRequest request) {
		Connection con = dao.connect();
	    JSONObject resultJSON = new JSONObject();


		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call doLogin(?, ?, ?)}");
			cs.setString(1, email);
			cs.setString(2, password);
			cs.setString(3, request.getSession().getId());
			cs.execute();

		    ResultSet rs = cs.getResultSet();
		    if (rs.next()) {
		    	
		    	 String result = rs.getString("code");
		    	 if (result.equals("L210")) {
			    	 int id = rs.getInt("id");
			    	 request.getSession().setAttribute("id", id);
		    	 }
		    	 
		    	 resultJSON.put("result", result);
		    };
		} catch (SQLException e) {
			e.printStackTrace();
			dao.close(con);
			return null;
		}
		dao.close(con);
		return resultJSON;
	}

	public boolean checkLogin(HttpServletRequest request) {
		Connection con = dao.connect();
		HttpSession session = request.getSession();
		
		boolean result = false;
		if (session.getAttribute("id") != null) {
			String idSession = session.getId();
			String id = Integer.toString((int) session.getAttribute("id"));
			try {
				CallableStatement cs = (CallableStatement) con.prepareCall("{call checkLogin(?, ?)}");
				cs.setString(1, id);
				cs.setString(2, idSession);

				cs.execute();
				
				ResultSet rs = cs.getResultSet();
				if (rs.next()) {
					if (rs.getInt("logins") > 0) {
						result = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		dao.close(con);
		return result;

	}

	public JSONObject register(String name, String surname, String tag, String birth, String email, String password,String bio,Part filePart) {
		Connection con = dao.connect();
		Essentials essentials = new Essentials();
		
	    JSONObject resultJSON = new JSONObject();
	    
	    byte[] image = null;
		try {
			image = essentials.tranformImageTo64(filePart);
			try {
				CallableStatement cs = (CallableStatement) con.prepareCall("{call createLogin(?, ?, ?, ?, ?, ?, ?, ?)}");
			    cs.setString(1, name);
			    cs.setString(2, surname);
			    cs.setString(3, tag);
			    cs.setDate(4, java.sql.Date.valueOf(birth));
			    cs.setString(5, email);
			    cs.setString(6, password);
			    cs.setString(7, bio);	 
			    cs.setBytes(8, image);
			    cs.execute();
			    
			    ResultSet rs = cs.getResultSet();
			    if (rs.next()) {
			        String responseCode = rs.getString(1);
			        resultJSON.put("responseCode", responseCode);
			        if (responseCode.indexOf("L10") > -1) {
			        	String textForUser = "";
			        	int email_finded = rs.getInt(2);
			        	if (email_finded > 0) {
			        		textForUser += "Email";
			        	}
			        	resultJSON.put("email_finded", email_finded);
			        	int tag_finded = rs.getInt(3);
			        	if (tag_finded > 0) {
			        		textForUser += ", Tag";
			        	}
			        	resultJSON.put("tag_finded", tag_finded);
			        	
			        	textForUser += " ja registrados tente outro...";
			        	resultJSON.put("textForUser", textForUser);
			        }else {
			        	resultJSON.put("textForUser", "Usuario Criado com sucesso");
			        }
			    }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	    

		return resultJSON;
		
	}
		
	public ArrayList<Book> getBooksUser(int id){
		Connection con = dao.connect();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call getBooks_person(?)}");
			cs.setInt(1, id);
			cs.execute();
		    ResultSet rs = cs.getResultSet();
			
		    ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				int idBook = rs.getInt("id");
				String name = rs.getString("name");
				byte[] cape = rs.getBytes("cape");
				String author = rs.getString("author");
				boolean selected = rs.getBoolean("selected");
				Book book = new Book(idBook, name, cape, author, selected);
				books.add(book);
		    };
		    return books;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Book> getBooksUserByTag(String tag){
		Connection con = dao.connect();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call getBooks_person_tag(?)}");
			cs.setString(1, tag);
			cs.execute();
		    ResultSet rs = cs.getResultSet();
			
		    ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				int idBook = rs.getInt("id");
				String name = rs.getString("name");
				byte[] cape = rs.getBytes("cape");
				int authorID = rs.getInt("authorId");
				String authorTag = rs.getString("tag");
				Book book = new Book(idBook, name, cape, authorID, authorTag);
				books.add(book);
		    };
		    return books;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public JSONObject updatePerson(Person person) {
		Connection con = dao.connect();
	    JSONObject resultJSON = new JSONObject();
	    try {
	    	CallableStatement cs = (CallableStatement) con.prepareCall("{CALL updatePerson(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
	           // Definir os valores dos parâmetros
	           cs.setInt(1, person.getId());
	           cs.setString(2, person.getName());
	           cs.setString(3, person.getSurname());
	           cs.setString(4,	person.getTag());
	           cs.setString(5, person.getBirth());
	           cs.setString(6, person.getEmail());
	           cs.setString(7, person.getPassword());
	           cs.setString(8, person.getBio());
	           cs.setString(9, person.getNewPassword());
	           cs.setBytes(10, person.getImage());
	           cs.execute();
	          

		} catch (SQLException e) {
			e.printStackTrace();
		}
	    dao.close(con);
		return resultJSON;
	}
	
	public ArrayList<Person> searchPerson(String name){
		ArrayList<Person> persons = new ArrayList<Person>();
		Connection con = dao.connect();
		
		try {
			CallableStatement cs =  (CallableStatement) con.prepareCall("{call searchPersonbyName(?)}");
			cs.setString(1, name);
			cs.execute();
			
			ResultSet rs = cs.getResultSet();
			
			while (rs.next()) {
				Person person = new Person(rs.getString("name"), rs.getString("surname"), rs.getString("tag"), rs.getBytes("image"));
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dao.close(con);
		return persons;
	}

}
