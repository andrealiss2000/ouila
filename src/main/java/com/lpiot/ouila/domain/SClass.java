package com.lpiot.ouila.domain;

import java.sql.Timestamp;
import java.util.Date;

public class SClass {
	  private int  id;
	  private Subject subject;
	  private User teacher;
	  private Timestamp start;
	  private Timestamp end;
	  
	public SClass(int id, Subject subject, User teacher, Date start, Date end) {
		this.id = id;
		this.subject = subject;
		this.teacher = teacher;
		this.start = new java.sql.Timestamp(start.getTime());
		this.end = new java.sql.Timestamp(end.getTime());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = new java.sql.Timestamp(start.getTime());
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = new java.sql.Timestamp(end.getTime());
	}
	
	
	  
	  
}
