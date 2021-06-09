package domain;

public class Subject {
	
	private int id; 
	private String name;
	

	public Subject(int course, String name) {
		super();
		this.id = course;
		this.name = name;
	}
	
	
	public int getCourse() {
		return id;
	}


	public void setCourse(int course) {
		this.id = course;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



}
