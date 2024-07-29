package com.cg.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.cg.demo.util.CommonUtil;

@SpringBootTest
//@TestPropertySource(properties = "emp.name=JohnDoe")
@TestPropertySource(locations = "classpath:application-test.properties")
class DemoServiceTest {
	
	@Autowired
	private DemoService demoService;
	
	/*
	 * @MockBean private CommonUtil commonUtil;
	 */
	

	@BeforeEach
	public void setUp() {
	}

	@AfterEach
	public void tearDown() {
	}

	@Test
	public void testProcessString() {
		// Arrange
		String input = "Test";
		String expected = "TestAppended"; // Assuming CommonUtil.appendString appends "Appended"
		//when(commonUtil.appendString(anyString())).thenReturn(expected);
		// Act
		String result = demoService.processString(input); // Replace DemoService with your actual service class
																// name
		System.out.println(result);
		// Assert
		assertEquals(expected, result);
		
	}

}
