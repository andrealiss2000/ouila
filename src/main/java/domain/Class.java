package domain;

import java.util.Date;

public class Class {
	  private int  id;
	  private Subject subject;
	  private User teacher;
	  private Date start;
	  private Date end;
	  
	public Class(int id, Subject subject, User teacher, Date start, Date end) {
		this.id = id;
		this.subject = subject;
		this.teacher = teacher;
		this.start = start;
		this.end = end;
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
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	  
	  
}
