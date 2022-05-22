package com.io.tedtalk.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.io.tedtalk.dto.LoginRequest;
import com.io.tedtalk.dto.TedTalkDto;
import com.io.tedtalk.service.TedTalkService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TedTalkControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private TedTalkService tedTalkService;

	@Test
	void findTedTalksTest() throws Exception {
		String accessToken = obtainAccessToken();
		TedTalkDto tedTalk = new TedTalkDto("id", "Title", "Dec 2021", "Author", 1l, 1l, "link");
		List<TedTalkDto> tedTalks = Arrays.asList(tedTalk);
		when(tedTalkService.findTedTalks(Mockito.anyString(), Mockito.anyString(), Mockito.anyLong(),
				Mockito.anyLong())).thenReturn(tedTalks);

		this.mockMvc.perform(get("/tedtalk").header("Authorization", "Bearer " + accessToken))
				.andExpect(status().isOk())

				.andDo(print());
	}

	private String obtainAccessToken() throws Exception {
		LoginRequest login = new LoginRequest();
		login.setUsername("iOAdmin");
		login.setPassword("05Pm032*");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(login);
		ResultActions result = mockMvc.perform(post("/api/auth/signin").content(json).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());

		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("token").toString();
	}
}