package com.codechallenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.codechallenge.config.Application;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Application.class })
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
