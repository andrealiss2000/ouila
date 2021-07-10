package com.lpiot.ouila.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

	@Column(nullable = false, unique = true, length = 8)
	private String cardNumber;

	@Column(unique = true, length = 10)
	private String phone;

	private String address;

	@ManyToOne(optional = true, targetEntity = Course.class)
	@JoinColumn(name = "course_id", nullable = false, referencedColumnName = "id")
	private Course course; // Formation

	@Column(nullable = false)
	private Role role;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, targetEntity = Presence.class)
	@JsonIgnore
	private List<Presence> presences;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role.toString()));
	}

}
