package com.io.tedtalk;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.io.tedtalk.controller.TedTalkController;

@SpringBootTest
class TedtalkServiceApplicationTests {

	@Autowired
	private TedTalkController tedTalkController;
	
	@Test
	void contextLoads() {
		assertThat(tedTalkController).isNotNull();
	}

}
