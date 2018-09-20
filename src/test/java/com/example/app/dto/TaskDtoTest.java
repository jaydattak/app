package com.example.app.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.app.entity.Task;

public class TaskDtoTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testHashCode() {
		TaskDto obj = new TaskDto();
		obj.setId(1);
		obj.setName("Task");
		assertEquals("Task".hashCode(), obj.hashCode());

		obj = new TaskDto();
		obj.setId(1);
		obj.setName(null);
		assertEquals(obj.hashCode(), obj.hashCode());

	}

	@Test
	public final void testEqualsObject() {
		TaskDto obj = new TaskDto();
		obj.setId(1);
		obj.setName("Task");

		TaskDto obj1 = new TaskDto();
		obj1.setId(1);
		obj1.setName("Task");
		assertEquals(true, obj.equals(obj1));
		
		TaskDto obj2 = new TaskDto();
		obj2.setId(1);
		obj2.setName("Task");
		assertEquals(true, obj2.getId() == obj.getId());
		
		TaskDto obj3 = new TaskDto();
		obj3.setId(3);
		obj3.setName("Task");
		assertEquals(false, obj3.getId() == obj.getId());

		TaskDto nullObj = null;
		assertEquals(false, obj.equals(nullObj));

		Task otherObj = new Task();
		assertEquals(false, obj.equals(otherObj));
	}

}
