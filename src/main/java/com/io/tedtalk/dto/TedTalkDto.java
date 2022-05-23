package com.io.tedtalk.dto;

import javax.validation.constraints.NotNull;

import com.io.tedtalk.constants.TedTalkConstants;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TedTalkDto {
	
	private String tedTalkId;

	@NotNull(message = TedTalkConstants.TITLE_NON_EMPTY)
	private String title;

	private String date;

	@NotNull(message = TedTalkConstants.AUTHOR_NON_EMPTY)
	private String author;

	private Long views;
	
	private Long likes;
	
	@NotNull(message = TedTalkConstants.LINK_NON_EMPTY)
	private String link;

	
}
