package com.felix180.person;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

@SpringBootTest
class PersonApplicationTests {

	void contextLoads() {
		Assertions.assertEquals("true","true");
	}

}
