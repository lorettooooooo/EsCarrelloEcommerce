package it.objectmethod.domain;

public class User {
	private String username;
	private Integer id;
	
	public void setUsername(String username) {
		this.username=username;
	}
	public String getUsername() {
		return username;
	}
	public void setId(int id) {
		this.id=id;
	}
	public Integer getId() {
		return id;
	}
}
