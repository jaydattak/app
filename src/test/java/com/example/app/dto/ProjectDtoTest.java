package com.example.app.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.app.entity.Project;
import com.example.app.entity.Task;

public class ProjectDtoTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testHashCode() {
		ProjectDto obj = new ProjectDto();
		obj.setId(1);
		obj.setName("Project");
		assertEquals("Project".hashCode(), obj.hashCode());
	}

	@Test
	public final void testEqualsObject() {
		ProjectDto obj = new ProjectDto();
		obj.setId(1);
		obj.setName("Project");

		ProjectDto obj1 = new ProjectDto();
		obj1.setId(1);
		obj1.setName("Project");
		assertEquals(true, obj.equals(obj1));

		ProjectDto nullObj = null;
		assertEquals(false, obj.equals(nullObj));

		Project otherObj = new Project();
		assertEquals(false, obj.equals(otherObj));
	}

}
