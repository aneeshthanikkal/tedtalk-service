package com.io.tedtalk.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.io.tedtalk.constants.RestURIConstants;
import com.io.tedtalk.constants.TedTalkConstants;
import com.io.tedtalk.dto.ResponseMessage;
import com.io.tedtalk.dto.TedTalkDto;
import com.io.tedtalk.exception.CommonBadRequestException;
import com.io.tedtalk.helper.CSVHelper;
import com.io.tedtalk.service.TedTalkService;

@RestController
public class TedTalkController implements BaseController {
	static final Log log = LogFactory.getLog(TedTalkController.class);

	private TedTalkService tedTalkService;

	public TedTalkController(TedTalkService tedTalkService) {
		this.tedTalkService = tedTalkService;
	}

	@PostMapping(path = RestURIConstants.TEDTALK)
	public ResponseEntity<ResponseMessage> saveTedTalk(@RequestParam("file") MultipartFile file) {
		if (CSVHelper.hasCSVFormat(file)) {
			tedTalkService.saveTedTalk(file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage(TedTalkConstants.TEDTALK_SAVED_SUCCESSFULLY));
		}
		throw new CommonBadRequestException(TedTalkConstants.INVALID_INPUT);

	}

	@GetMapping(path = RestURIConstants.TEDTALK + RestURIConstants.ID)
	public ResponseEntity<TedTalkDto> findTedTalkById(@PathVariable(name = "id") String id) {
		return new ResponseEntity<>(tedTalkService.findTedTalkById(id), HttpStatus.OK);
	}

	@GetMapping(path = RestURIConstants.TEDTALK)
	public ResponseEntity<List<TedTalkDto>> findTedTalks(
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "views", required = false) Long views,
			@RequestParam(value = "likes", required = false) Long likes) {
		return new ResponseEntity<>(tedTalkService.findTedTalks(author, title, views, likes), HttpStatus.OK);
	}

}
