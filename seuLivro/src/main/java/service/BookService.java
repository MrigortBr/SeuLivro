package service;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;

import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.protocol.Resultset;

import model.DAO;
import object.Book;

public class BookService {
	DAO dao = new DAO();
	
	public JSONObject updateBook(Book book) {
		Connection con = dao.connect();
		JSONObject JSONReturn = new JSONObject();
		try {
			if (book.getId() != 0) {
				CallableStatement cs = (CallableStatement) con.prepareCall("{call updateBook(?, ?, ?, ?, ?, ?, ?)}");
				cs.setInt(1, book.getId());
				cs.setString(2, book.getName());
				cs.setBytes(3, book.getCape());
				cs.setBlob(4, book.getPdf());
				cs.setString(5, book.getAuthor());
				cs.setInt(6, book.getAuthorId());
				cs.setString(7, book.getDescription());
				cs.execute();
				ResultSet rs = cs.getResultSet();

			}else {
				CallableStatement cs = (CallableStatement) con.prepareCall("{call createBook(?, ?, ?, ?, ?, ?)}");
				cs.setString(1, book.getName());
				cs.setBytes(2, book.getCape());
				cs.setBlob(3, book.getPdf());
				cs.setString(4, book.getAuthor());
				cs.setInt(5, book.getAuthorId());
				cs.setString(6, book.getDescription());
				cs.execute();
				ResultSet rs = cs.getResultSet();
			}
			dao.close(con);


		} catch (SQLException e) {
			e.printStackTrace();
			dao.close(con);
		}
		
		return JSONReturn;
	}

	public JSONObject deleteBook(int idBook, int idPerson) {
		Connection con = dao.connect();
		JSONObject JSONReturn = new JSONObject();
		
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call deleteBook(?,?)}");
			cs.setInt(1, idBook);
			cs.setInt(2, idPerson);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.close(con);
		return JSONReturn;
	}
	
	public Book getBookById(int id, int idPerson) {
		Connection con = dao.connect();
		Book book = null;
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call getBookbyid(?, ?)}");
			cs.setInt(1, id);
			cs.setInt(2, idPerson);
			cs.execute();
			
			ResultSet rs = cs.getResultSet();
			
			if (rs.next()) {
				int idBook = rs.getInt("id");
				String name = rs.getString("name");
				byte[] cape = rs.getBytes("cape");
				String tag = rs.getString("tag");
				String author = rs.getString("author");
				String description = rs.getString("description");
				float avgStars = rs.getFloat("avgstars");
				int sumLikes = rs.getInt("sumlikes");
				int sumDeslikes = rs.getInt("sumdeslikes");
				if (idPerson != 0) {
					try {
						String rate = rs.getString("rate");
						int stars = rs.getInt("stars");
						boolean selected = rs.getBoolean("selected");
						book = new Book(idBook, name, cape, author, tag, description, rate, stars, selected, avgStars, sumLikes, sumDeslikes);
					} catch (SQLException e) {
						book = new Book(idBook, name, cape, author, tag, description, avgStars, sumLikes, sumDeslikes);
					}
	
					
				}else {
				book = new Book(idBook, name, cape, author, tag, description, avgStars, sumLikes, sumDeslikes);
				}
			}
			dao.close(con);

			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public InputStream getBookByIdPdf(int id, int idUser) {
		Connection con = dao.connect();
		InputStream stream = null;
		try {
			CallableStatement cs = (CallableStatement) con.prepareCall("{call getBookByidPDF(?, ?)}");
			cs.setInt(1, id);
			cs.setInt(2, idUser);
			cs.execute();
			
			ResultSet rs = cs.getResultSet();
			if (rs.next()) {
				stream = rs.getBlob("pdf").getBinaryStream();
			}
			
			dao.close(con);

			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stream;
	}

	public void rate(String rate, int star, int idBook, int idPerson) {
		Connection con = dao.connect();
		
		try {
			CallableStatement cs =(CallableStatement) con.prepareCall("{call voteBook(?, ?, ?, ?)}");
			cs.setInt(1, idPerson);
			cs.setInt(2, idBook);
			cs.setInt(3, star);
			cs.setString(4, rate);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dao.close(con);
	}

	public ArrayList<Book> getBooksByAuthorId(int id){
		ArrayList<Book> books = new ArrayList<Book>();
		Connection con = dao.connect();
		
		
		CallableStatement cs;
		try {
			cs = (CallableStatement) con.prepareCall("{call getBooksCreator(?)}");
			cs.setInt(1, id);
			cs.execute();
			
			ResultSet rs = cs.getResultSet();
			
			while(rs.next()){
				books.add(new Book(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getBytes("cape"),
						rs.getBlob("pdf").getBinaryStream(),
						rs.getString("author"),
						rs.getInt("authorId"),
						rs.getString("description")
				));
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dao.close(con);
		return books;
		
	}

	public void unSelectBook(int idBook, int idPerson) {
		Connection con = dao.connect();
		
		try {
			CallableStatement cs =(CallableStatement) con.prepareCall("{call unSelectBook(?, ?)}");
			cs.setInt(1, idBook);
			cs.setInt(2, idPerson);
			cs.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dao.close(con);
	}

	public ArrayList<Book> searchBook(String name){
		ArrayList<Book> books = new ArrayList<Book>();
		Connection con = dao.connect();
		
		try {
			CallableStatement cs =  (CallableStatement) con.prepareCall("{call searchBookbyName(?)}");
			cs.setString(1, name);
			cs.execute();
			
			ResultSet rs = cs.getResultSet();
			
			while (rs.next()) {
				Book book = new Book(rs.getInt("id"), rs.getBytes("cape"));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dao.close(con);
		return books;
	}

}
