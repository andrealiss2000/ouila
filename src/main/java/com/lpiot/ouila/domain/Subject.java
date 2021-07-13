package com.lpiot.ouila.domain;

public class Subject { 
	private int id; 
	private String name;
	

	public Subject(int course, String name) {
		super();
		this.id = course;
		this.name = name;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int course) {
		this.id = course;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

 

}
