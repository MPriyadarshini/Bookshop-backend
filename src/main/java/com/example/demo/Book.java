package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String bookname;
	private String price;
	private String author;
	private String edition;

	private String address;
	private String phone;
	private String other;
	
	@Column(length=1000000)
	private byte[] image;

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int id, String bookname, String price, String author, String edition, String address, String phone,
			String other, byte[] image) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.price = price;
		this.author = author;
		this.edition = edition;
		this.address = address;
		this.phone = phone;
		this.other = other;
		this.image = image;
	}

	public Book(String bookname, String price, String author, String edition, String address, String phone,
			String other, byte[] image) {
		super();
		this.bookname = bookname;
		this.price = price;
		this.author = author;
		this.edition = edition;
		this.address = address;
		this.phone = phone;
		this.other = other;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
	
	
}
