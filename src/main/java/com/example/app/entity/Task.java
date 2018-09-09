package com.example.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "task")
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3634396644022265222L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Task_ID")
	private int id;

	@Column(name = "Task")
	private String name;

	@ManyToOne(targetEntity = ParentTask.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "Parent_ID")
	private ParentTask parentTask;

	@ManyToOne(targetEntity = Project.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "Project_ID")
	private Project project;

	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "User_ID")
	private User user;

	@Temporal(TemporalType.DATE)
	@Column(name = "startDate")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "endDate")
	private Date endDate;

	@Column(name = "priority")
	private int priority;

	@Column(name = "status")
	private String status;

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

	public ParentTask getParentTask() {
		return parentTask;
	}

	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
