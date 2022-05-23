package com.io.tedtalk.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.io.tedtalk.constants.TedTalkConstants;
import com.io.tedtalk.dto.TedTalkDto;
import com.io.tedtalk.exception.CommonBadRequestException;
import com.io.tedtalk.exception.CommonResourceNotFoundException;
import com.io.tedtalk.model.TedTalk;
import com.io.tedtalk.repository.TedTalkRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TedTalkService {

	private static final Log log = LogFactory.getLog(TedTalkService.class);

	private final TedTalkRepository tedTalkRepository;

	private final CSVService csvService;
	
    private final ModelMapper modelMapper;

	public void saveTedTalk(MultipartFile file) {
		try {
			if (csvService.hasCSVFormat(file)) {
				List<TedTalk> tedTalks = csvService.mapCsvToTedtalk(file.getInputStream());
				tedTalkRepository.saveAll(tedTalks);
			}
		} catch (IOException e) {
			log.error("TedTalkController : saveTedTalk() ", e);
			throw new CommonBadRequestException(TedTalkConstants.INVALID_INPUT);
		}
	}

	public TedTalkDto findTedTalkById(String id) {
		TedTalk tedTalk = tedTalkRepository.findById(id)
				.orElseThrow(() -> new CommonResourceNotFoundException(TedTalkConstants.TEDTALK_NOT_FOUND));
		return modelMapper.map(tedTalk, TedTalkDto.class);
	}

	public List<TedTalkDto> findTedTalks(String author, String title, Long views, Long likes) {
		List<TedTalk> tedTalks = tedTalkRepository.findByAuthorAndTitleAndViewsAndLikes(Optional.ofNullable(author),
				Optional.ofNullable(title), Optional.ofNullable(views), Optional.ofNullable(likes));
		if (tedTalks.size() == 0) {
			throw new CommonResourceNotFoundException(TedTalkConstants.TEDTALK_NOT_FOUND);
		}
		return tedTalks.stream().map(tedTalk -> modelMapper.map(tedTalk, TedTalkDto.class))
				.collect(Collectors.toList());
	}

	public void deleteTedTalk(String id) {
		if (tedTalkRepository.existsById(id)) {
			tedTalkRepository.deleteById(id);
		}
		throw new CommonResourceNotFoundException(TedTalkConstants.TEDTALK_NOT_FOUND);
	}

	public TedTalkDto updateTedTalk(String id, TedTalkDto tedTalkDto) {
		if (tedTalkRepository.existsById(id)) {
			tedTalkDto.setTedTalkId(id);
			tedTalkRepository.save(createTedTalk(tedTalkDto));
		}
		return tedTalkDto;
	}

	private TedTalk createTedTalk(TedTalkDto tedTalkDto) {
		return TedTalk.builder().author(tedTalkDto.getAuthor()).date(tedTalkDto.getDate()).likes(tedTalkDto.getLikes())
				.link(tedTalkDto.getLink()).title(tedTalkDto.getTitle()).views(tedTalkDto.getViews())
				.build();
	}
}
