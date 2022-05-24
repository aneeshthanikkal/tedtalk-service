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
import org.modelmapper.ModelMapper;

import com.io.tedtalk.dto.TedTalkDto;
import com.io.tedtalk.model.TedTalk;
import com.io.tedtalk.repository.TedTalkRepository;

@ExtendWith(MockitoExtension.class)
public class TedTalkServiceTest {
	
	@InjectMocks
	TedTalkService service;

	@Mock
	TedTalkRepository dao;
	
	@Mock
	ModelMapper modelMapper;

	@Test
	public void testFindTedTalks() {
		List<TedTalk> tedTalks = Arrays.asList(findTedTalk());
		when(dao.findTedTalksByAuthorTitleViewsAndLikes(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(tedTalks);
		when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(findTedTalkDto());

		List<TedTalkDto> tedTalkList = service.findTedTalks(null, null, null, null);
		assertEquals(1, tedTalkList.size());
	}
	
	@Test
	public void testFindTedTalkById() {
		Optional<TedTalk> tedTalkOpt = Optional.of(findTedTalk());
		when(dao.findById(Mockito.anyString())).thenReturn(tedTalkOpt);
		when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(findTedTalkDto());
		TedTalkDto tedTalkDto = service.findTedTalkById("id");
		assertEquals("id", tedTalkDto.getTedTalkId());
	}

	private TedTalk findTedTalk() {
		return TedTalk.builder().author("Author")
				.date("Dec 2021")
				.likes(1l)
				.link("link").title("Title")
				.views(1l).tedTalkId("id")
				.build();
	}
	
	private TedTalkDto findTedTalkDto() {
		return TedTalkDto.builder().author("Author")
				.date("Dec 2021")
				.likes(1l)
				.link("link").title("Title")
				.views(1l).tedTalkId("id")
				.build();
	}
}