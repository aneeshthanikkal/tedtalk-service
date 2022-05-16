package com.io.tedtalk.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.io.tedtalk.constants.TedTalkConstants;
import com.io.tedtalk.dto.TedTalkDto;
import com.io.tedtalk.exception.CommonBadRequestException;
import com.io.tedtalk.exception.CommonResourceNotFoundException;
import com.io.tedtalk.helper.CSVHelper;
import com.io.tedtalk.model.TedTalk;
import com.io.tedtalk.repository.TedTalkRepository;
import com.io.tedtalk.service.TedTalkService;

@Service
public class TedTalkServiceImpl implements TedTalkService {

	static final Log log = LogFactory.getLog(TedTalkServiceImpl.class);

	@Resource
	TedTalkRepository tedTalkRepository;

	@Override
	public void saveTedTalk(MultipartFile file) {
		try {
			List<TedTalk> tutorials = CSVHelper.csvToTedtalk(file.getInputStream());
			tedTalkRepository.saveAll(tutorials);
		} catch (IOException e) {
			log.error("TedTalkController : saveTedTalk() " + e);
			throw new CommonBadRequestException(TedTalkConstants.INVALID_INPUT);
		}
	}

	@Override
	public TedTalkDto findTedTalkById(String id) {
		Optional<TedTalk> tedTalkOpt = tedTalkRepository.findById(id);
		TedTalk tedTalk = tedTalkOpt
				.orElseThrow(() -> new CommonResourceNotFoundException(TedTalkConstants.TEDTALK_NOT_FOUND));
		return createTedTalkDto(tedTalk);
	}

	private TedTalkDto createTedTalkDto(TedTalk tedTalk) {
		return new TedTalkDto(tedTalk.getTedTalkId(), tedTalk.getTitle(), tedTalk.getDate(), tedTalk.getAuthor(),
				tedTalk.getViews(), tedTalk.getLikes(), tedTalk.getLink());
	}

	@Override
	public List<TedTalkDto> findTedTalks(String author, String title, Long views, Long likes) {
		List<TedTalk> tedTalks = tedTalkRepository.findByAuthorAndTitleAndViewsAndLikes(Optional.ofNullable(author),
				Optional.ofNullable(title), Optional.ofNullable(views), Optional.ofNullable(likes));
		if (tedTalks.size() == 0) {
			throw new CommonResourceNotFoundException(TedTalkConstants.TEDTALK_NOT_FOUND);
		}
		List<TedTalkDto> tedTalkDtoList = new ArrayList<>();
		for (TedTalk tedTalk : tedTalks) {
			tedTalkDtoList.add(createTedTalkDto(tedTalk));
		}
		return tedTalkDtoList;
	}

	@Override
	public void deleteTedTalk(String id) {
		Optional<TedTalk> tedTalkOpt = tedTalkRepository.findById(id);
		TedTalk tedTalk = tedTalkOpt
				.orElseThrow(() -> new CommonResourceNotFoundException(TedTalkConstants.TEDTALK_NOT_FOUND));
		tedTalkRepository.delete(tedTalk);

	}

	@Override
	public TedTalkDto updateTedTalk(String id, TedTalkDto tedTalkDto) {
		Optional<TedTalk> tedTalkOpt = tedTalkRepository.findById(id);
		TedTalk tedTalk = tedTalkOpt
				.orElseThrow(() -> new CommonResourceNotFoundException(TedTalkConstants.TEDTALK_NOT_FOUND));
		tedTalkRepository.save(createTedTalk(tedTalkDto, tedTalk.getTedTalkId()));
		tedTalkDto.setTedTalkId(id);
		return tedTalkDto;
	}

	private TedTalk createTedTalk(TedTalkDto tedTalkDto, String tedTalkId) {
		TedTalk tedTalk = new TedTalk();
		tedTalk.setAuthor(tedTalkDto.getAuthor());
		tedTalk.setDate(tedTalkDto.getDate());
		tedTalk.setLikes(tedTalkDto.getLikes());
		tedTalk.setLink(tedTalkDto.getLink());
		tedTalk.setTedTalkId(tedTalkDto.getTedTalkId());
		tedTalk.setTitle(tedTalkDto.getTitle());
		tedTalk.setViews(tedTalkDto.getViews());
		tedTalk.setTedTalkId(tedTalkId);
		return tedTalk;
	}
}
