package com.example.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void mainTest() {
		MainApplication.main(new String[] {});
	}

}
