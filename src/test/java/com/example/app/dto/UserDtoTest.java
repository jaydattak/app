package com.example.app.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.app.entity.User;

public class UserDtoTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testHashCode() {
		UserDto obj = new UserDto();
		obj.setId(1);
		obj.setFirstName("User");
		assertEquals("User".hashCode(), obj.hashCode());

		obj = new UserDto();
		obj.setId(1);
		obj.setFirstName(null);
		assertEquals(obj.hashCode(), obj.hashCode());
	}

	@Test
	public final void testEqualsObject() {
		UserDto obj = new UserDto();
		obj.setId(1);
		obj.setFirstName("User");

		UserDto obj1 = new UserDto();
		obj1.setId(1);
		obj1.setFirstName("User");
		assertEquals(true, obj.equals(obj1));
		
		
		UserDto obj2 = new UserDto();
		obj2.setId(1);
		obj2.setFirstName("User");
		assertEquals(true, obj2.getId() == obj.getId());
		
		UserDto obj3 = new UserDto();
		obj3.setId(3);
		obj3.setFirstName("User");
		assertEquals(false, obj3.getId() == obj.getId());

		UserDto nullObj = null;
		assertEquals(false, obj.equals(nullObj));

		User otherObj = new User();
		assertEquals(false, obj.equals(otherObj));
	}

}
