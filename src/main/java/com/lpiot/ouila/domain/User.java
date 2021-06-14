package com.lpiot.ouila.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String cardNumber;

	@Column(unique = true)
	private Integer phone;

	private String address;

	@ManyToOne(optional = false, targetEntity = Course.class)
	@JoinColumn(name = "course_id", nullable = false, referencedColumnName = "id")
	private Course course; // Formation

	@ManyToOne(optional = false, targetEntity = Role.class)
	@JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
	private Role role;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, targetEntity = Presence.class)
	private List<Presence> presences;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [address=" + address + ", cardNumber=" + cardNumber + ", course=" + course + ", email=" + email
				+ ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName + ", password=" + password
				+ ", phone=" + phone + ", role=" + role + ", username=" + username + "]";
	}

	public User() {
	}

	public User(String firstName, String lastName, String email, String username, String password, String cardNumber,
			Integer phone, String address, Course course, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.cardNumber = cardNumber;
		this.phone = phone;
		this.address = address;
		this.course = course;
		this.role = role;
	}

}
