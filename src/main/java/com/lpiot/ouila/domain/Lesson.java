package com.lpiot.ouila.domain;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, targetEntity = User.class)
	@JoinColumn(name = "teacher_id", nullable = false, referencedColumnName = "id")
	private User teacher;

	@ManyToOne(optional = false, targetEntity = Subject.class)
	@JoinColumn(name = "subject_id", nullable = false, referencedColumnName = "id")
	private Subject subject;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date startDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date endDateTime;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, targetEntity = Presence.class)
	@JsonIgnore
	private List<Presence> presences;

}
