package com.lpiot.ouila.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private List<Presence> presences;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	@Override
	public String toString() {
		return "Lesson [endDateTime=" + endDateTime + ", id=" + id + ", startDateTime=" + startDateTime + ", subject="
				+ subject + ", teacher=" + teacher + "]";
	}

	public Lesson(User teacher, Subject subject, Date startDateTime, Date endDateTime) {
		this.teacher = teacher;
		this.subject = subject;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}

	public Lesson() {
	}

}
