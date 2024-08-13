package com.notquite.notquiteservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class NotquiteserviceApplicationTests {

	@BeforeAll
	public static void setUp(){
		System.out.println("Put set up stuff here.");
	}

	@Test
	public void dtoTest() {

	}

}
