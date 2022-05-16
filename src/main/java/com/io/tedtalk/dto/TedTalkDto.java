package com.io.tedtalk.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.io.tedtalk.constants.TedTalkConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class TedTalkDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 917751056631431767L;

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

	public TedTalkDto(String tedTalkId, String title, String date, String author, Long views, Long likes,
			String link) {
		this.tedTalkId = tedTalkId;
		this.title = title;
		this.date = date;
		this.author = author;
		this.views = views;
		this.likes = likes;
		this.link = link;
	}
	
	
}
