package com.lpiot.ouila.domain;

import java.sql.Blob;


public class Justification {
	private int id;
	private String comment;
	private Blob document;
	
	public Justification(int id, String comment, Blob document) { 
		this.id = id;
		this.comment = comment;
		this.document = document;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Blob getDocument() {
		return document;
	}

	public void setDocument(Blob document) {
		this.document = document;
	}
	
	
	
}
