package Model;

import java.util.Date;

public abstract class Person {
	private String id; // Mã số 
	private String name;
	private String email;
	private String phone;
	private String address;
	private String aid; // Thứ tự
	public Person() {
	}
	public Person(Person x) {
		this.id = x.id;
		this.name = x.name;
		this.email = x.email;
		this.address = x.address;
		this.aid = x.aid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	
}
