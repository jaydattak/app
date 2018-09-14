package com.example.app.dto;

public class ParentTaskDto {

	private int id;

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

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof ParentTaskDto) {
			ParentTaskDto dto = (ParentTaskDto) obj;
			return dto.getId() == this.id;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return this.name != null ? this.name.hashCode() : super.hashCode();
	}
}
