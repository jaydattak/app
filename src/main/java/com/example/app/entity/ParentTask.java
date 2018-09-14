package com.example.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parent_task")
public class ParentTask implements Serializable {
	
	private static final long serialVersionUID = -4764922937633960666L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "Parent_ID")
	private int id;
	
	@Column(name = "Parent_Task", nullable = false, unique = true)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
