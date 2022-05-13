package com.io.tedtalk.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.io.tedtalk.dto.TedTalkDto;

public interface TedTalkService {

	void saveTedTalk(MultipartFile file);

	TedTalkDto findTedTalkById(String id);

	List<TedTalkDto> findTedTalks(String author, String title, Long views, Long likes);

	void deleteTedTalk(String id);

	TedTalkDto updateTedTalk(String id, TedTalkDto tedTalkDto);

}
