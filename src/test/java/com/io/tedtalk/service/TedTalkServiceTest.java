package com.io.tedtalk.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.io.tedtalk.dto.TedTalkDto;
import com.io.tedtalk.model.TedTalk;
import com.io.tedtalk.repository.TedTalkRepository;
import com.io.tedtalk.service.TedTalkService;

@ExtendWith(MockitoExtension.class)
public class TedTalkServiceTest {
	@InjectMocks
	TedTalkService service;

	@Mock
	TedTalkRepository dao;

	@Test
	public void testFindTedTalks() {
		TedTalk tedTalk = new TedTalk("Title", "Dec 2021", "Author", 1l, 1l, "link", "id");
		List<TedTalk> tedTalks = Arrays.asList(tedTalk);
		when(dao.findByAuthorAndTitleAndViewsAndLikes(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(tedTalks);
		List<TedTalkDto> tedTalkList = service.findTedTalks(null, null, null, null);
		assertEquals(1, tedTalkList.size());
	}
	
	@Test
	public void testFindTedTalkById() {
		TedTalk tedTalk = new TedTalk("Title", "Dec 2021", "Author", 1l, 1l, "link", "id");
		Optional<TedTalk> tedTalkOpt = Optional.of(tedTalk);
		when(dao.findById(Mockito.anyString())).thenReturn(tedTalkOpt);
		TedTalkDto tedTalkDto = service.findTedTalkById("id");
		assertEquals("id", tedTalkDto.getTedTalkId());
	}

	
}