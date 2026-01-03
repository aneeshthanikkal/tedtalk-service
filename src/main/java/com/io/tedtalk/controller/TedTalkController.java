package com.io.tedtalk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.io.tedtalk.constants.TedTalkConstants;
import com.io.tedtalk.dto.TedTalkDto;
import com.io.tedtalk.service.TedTalkService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tedtalk")
@RequiredArgsConstructor
public class TedTalkController {

	private final TedTalkService tedTalkService;
	
	@GetMapping(path = "test", produces = "application/vnd.company.app-v1+json")
	public ResponseEntity<String> test() {
		return new ResponseEntity<>(TedTalkConstants.TEDTALK_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}


	@PostMapping(path = "import", produces = "application/vnd.company.app-v1+json")
	@PreAuthorize("hasRole('ROLE_WRITE')")
	public ResponseEntity<String> saveTedTalk(@RequestParam("file") MultipartFile file) {
		tedTalkService.saveTedTalk(file);
		return new ResponseEntity<>(TedTalkConstants.TEDTALK_SAVED_SUCCESSFULLY, HttpStatus.CREATED);
	}

	@GetMapping(path = "{id}", produces = "application/vnd.company.app-v1+json")
	@PreAuthorize("hasRole('ROLE_READ')")
	public ResponseEntity<TedTalkDto> findTedTalkById(@PathVariable(name = "id") String id) {
		return new ResponseEntity<>(tedTalkService.findTedTalkById(id), HttpStatus.OK);
	}

	@PutMapping(path = "{id}", produces = "application/vnd.company.app-v1+json")
	@PreAuthorize("hasRole('ROLE_WRITE')")
	public ResponseEntity<TedTalkDto> updateTedTalk(@PathVariable(name = "id") String id,
			@Valid @RequestBody TedTalkDto tedTalkDto) {
		return new ResponseEntity<>(tedTalkService.updateTedTalk(id, tedTalkDto), HttpStatus.OK);
	}

	@DeleteMapping(path = "{id}", produces = "application/vnd.company.app-v1+json")
	@PreAuthorize("hasRole('ROLE_WRITE')")
	public ResponseEntity<String> deleteTedTalk(@PathVariable(name = "id") String id) {
		tedTalkService.deleteTedTalk(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(produces = "application/vnd.company.app-v1+json")
	@PreAuthorize("hasRole('ROLE_READ') or hasRole('ROLE_WRITE')")
	public ResponseEntity<List<TedTalkDto>> findTedTalks(
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "views", required = false) Long views,
			@RequestParam(value = "likes", required = false) Long likes) {
		return new ResponseEntity<>(tedTalkService.findTedTalks(author, title, views, likes), HttpStatus.OK);
	}

}
