package com.roytuts.jsf.hibernate.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cds")
public class Cds implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String title;

	private String interpret;

	public Cds() {
	}

	public Cds(String title, String interpret) {
		this.title = title;
		this.interpret = interpret;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInterpret() {
		return this.interpret;
	}

	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}

}
