package object;

import java.io.InputStream;

import jakarta.servlet.http.Part;

public class Book {
	private int id;
	private String name;
	private byte[] cape;
	private InputStream  pdf;
	private String author;
	private String tag;
	private int authorId;
	private String description;
	private String rate;
	private int Stars;
	private boolean selected;
	private float avgStars;
	private int sumLikes;
	private int sumUnlikes;
	
	
	public Book(int id, byte[] cape) {
		super();
		this.id = id;
		this.cape = cape;
	}
	
	public Book(int id, String name, byte[] cape, String author, boolean selected) {
		super();
		this.id = id;
		this.name = name;
		this.cape = cape;
		this.author = author;
		this.selected = selected;
	}
	
	public Book(int id, String name, byte[] cape, int authorId, String tag) {
		super();
		this.id = id;
		this.name = name;
		this.cape = cape;
		this.tag = author;
		this.authorId = authorId;
	}
	

		
	public Book(String name, byte[] cape, InputStream pdf, String author, int authorId, String description) {
		super();
		this.name = name;
		this.cape = cape;
		this.pdf = pdf;
		this.author = author;
		this.authorId = authorId;
		this.description = description;
	}


	public Book(int id, String name, byte[] cape, InputStream pdf, String author, int authorId, String description) {
		super();
		this.id = id;
		this.name = name;
		this.cape = cape;
		this.pdf = pdf;
		this.author = author;
		this.authorId = authorId;
		this.description = description;
	}
	


	public Book(int id, String name, byte[] cape, String author, String tag, String description, String rate, int stars,
			boolean selected, float avgStars, int sumLikes, int sumUnlikes) {
		super();
		this.id = id;
		this.name = name;
		this.cape = cape;
		this.author = author;
		this.tag = tag;
		this.description = description;
		this.rate = rate;
		Stars = stars;
		this.selected = selected;
		this.avgStars = avgStars;
		this.sumLikes = sumLikes;
		this.sumUnlikes = sumUnlikes;
	}

	public Book(int id, String name, byte[] cape, String author, String tag, String description, float avgStars,
			int sumLikes, int sumUnlikes) {
		super();
		this.id = id;
		this.name = name;
		this.cape = cape;
		this.author = author;
		this.tag = tag;
		this.description = description;
		this.avgStars = avgStars;
		this.sumLikes = sumLikes;
		this.sumUnlikes = sumUnlikes;
	}









	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public byte[] getCape() {
		return cape;
	}


	public void setCape(byte[] cape) {
		this.cape = cape;
	}


	public InputStream getPdf() {
		return pdf;
	}


	public void setPdf(InputStream pdf) {
		this.pdf = pdf;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public int getAuthorId() {
		return authorId;
	}


	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getRate() {
		return rate;
	}


	public void setRate(String rate) {
		this.rate = rate;
	}


	public int getStars() {
		return Stars;
	}


	public void setStars(int stars) {
		Stars = stars;
	}


	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	public float getAvgStars() {
		return avgStars;
	}


	public void setAvgStars(float avgStars) {
		this.avgStars = avgStars;
	}


	public int getSumLikes() {
		return sumLikes;
	}


	public void setSumLikes(int sumLikes) {
		this.sumLikes = sumLikes;
	}


	public int getSumUnlikes() {
		return sumUnlikes;
	}


	public void setSumUnlikes(int sumUnlikes) {
		this.sumUnlikes = sumUnlikes;
	}
}
