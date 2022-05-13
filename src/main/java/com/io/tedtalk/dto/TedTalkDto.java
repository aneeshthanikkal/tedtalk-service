package com.io.tedtalk.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.io.tedtalk.constants.TedTalkConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

	private Instant date;

	@NotNull(message = TedTalkConstants.AUTHOR_NON_EMPTY)
	private String author;

	@Positive(message = "")
	private Long views;
	
	@Positive(message = "")
	private Long likes;
	
	@NotNull(message = TedTalkConstants.LINK_NON_EMPTY)
	private String link;
	
}
