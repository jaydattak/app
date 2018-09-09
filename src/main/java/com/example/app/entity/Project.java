package com.example.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "project")
public class Project implements Serializable {

	private static final long serialVersionUID = -5324328470958273914L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Project_ID")
	private int id;

	@Column(name = "Project", unique = true)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "startDate")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "endDate")
	private Date endDate;

	@Column(name = "priority")
	private int priority;

	@ManyToOne(targetEntity = User.class)
	@JoinTable(name = "PROJECT_MANAGER", joinColumns = @JoinColumn(name = "Project_ID", referencedColumnName = "Project_ID"), inverseJoinColumns = @JoinColumn(name = "Manager_ID", referencedColumnName = "User_ID"))
	private User manager;

	@OneToMany(targetEntity = Task.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "Project_ID", referencedColumnName = "Project_ID")
	private Set<Task> tasks = new HashSet<Task>();

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

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}
