package com.io.tedtalk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_TEDTALK")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class TedTalk {

	@Id
	@Column(name = "TEDTALK_ID", length=36)
	private String tedTalkId;

	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DATE")
	private String date;
	
	@Column(name = "AUTHOR")
	private String author;
	
	@Column(name = "VIEWS")
	private Long views;
	
	@Column(name = "LIKES")
	private Long likes;
	
	@Column(name = "LINK")
	private String link;

	public TedTalk(String tedTalkId, String title, String date, String author, Long views, Long likes, String link) {
		this.tedTalkId = tedTalkId;
		this.title = title;
		this.date = date;
		this.author = author;
		this.views = views;
		this.likes = likes;
		this.link = link;
	}

}
