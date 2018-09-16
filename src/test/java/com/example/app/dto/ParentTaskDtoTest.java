package com.example.app.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.app.entity.ParentTask;

public class ParentTaskDtoTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testHashCode() {
		ParentTaskDto obj = new ParentTaskDto();
		obj.setId(1);
		obj.setName("ParentTask");
		assertEquals("ParentTask".hashCode(), obj.hashCode());
		
		obj = new ParentTaskDto();
		obj.setId(1);
		obj.setName(null);
		assertEquals(obj.hashCode(), obj.hashCode());
	}

	@Test
	public final void testEqualsObject() {
		ParentTaskDto obj = new ParentTaskDto();
		obj.setId(1);
		obj.setName("ParentTask");
		
		ParentTaskDto obj1 = new ParentTaskDto();
		obj1.setId(1);
		obj1.setName("ParentTask");
		assertEquals(true, obj.equals(obj1));
		
		ParentTaskDto nullObj = null;
		assertEquals(false, obj.equals(nullObj));

		ParentTask otherObj = new ParentTask();
		assertEquals(false, obj.equals(otherObj));
	}

}
