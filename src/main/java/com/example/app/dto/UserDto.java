package com.example.app.dto;

public class UserDto {

	private int id;

	private String firstName;

	private String lastName;

	private String employeeId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof UserDto) {
			UserDto dto = (UserDto) obj;
			return dto.getId() == this.id;
		} else {
			return false;
		}

	}

	@Override
	public int hashCode() {
		return this.firstName != null ? this.firstName.hashCode() : super.hashCode();
	}

}
