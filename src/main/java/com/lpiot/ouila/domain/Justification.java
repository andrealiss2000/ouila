package com.lpiot.ouila.domain;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Justification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String comment;

	private Blob document;

	@OneToOne(mappedBy = "justification", targetEntity = Presence.class)
	private Presence presence;

	public Justification() {
	}

	public Justification(String comment, Blob document) {
		this.comment = comment;
		this.document = document;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Justification [comment=" + comment + ", document=" + document + ", id=" + id + "]";
	}

}
