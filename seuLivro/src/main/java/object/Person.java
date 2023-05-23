package object;

public class Person {
	private int id;
	private String name;
	private String surname;
	private String tag;
	private String birth;
	private String email;
	private String password;
	private String newPassword;
	private String bio;
	private byte[] image;
	private String imageLink;
	
	public Person(String name, String surname, String tag, byte[] image) {
		super();
		this.name = name;
		this.surname = surname;
		this.tag = tag;
		this.image = image;
	}
	
	public Person(int id, String name, String surname, String tag, String birth, String email, String password,
			String bio, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.tag = tag;
		this.birth = birth;
		this.email = email;
		this.password = password;
		this.bio = bio;
		this.image = image;
	}
	
	public Person(int id, String name, String surname, String tag, String birth, String email, String password,
			String bio, byte[] image, String newPassword) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.tag = tag;
		this.birth = birth;
		this.email = email;
		this.password = password;
		this.bio = bio;
		this.image = image;
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
