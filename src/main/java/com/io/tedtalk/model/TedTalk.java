package com.io.tedtalk.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "T_TEDTALK")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class TedTalk implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2963992274539846470L;

	@Id
	@Column(name = "TEDTALK_ID", length=36)
	private String tedTalkId;

	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DATE")
	private Instant date;
	
	@Column(name = "AUTHOR")
	private String author;
	
	@Column(name = "VIEWS")
	private Long views;
	
	@Column(name = "LIKES")
	private Long likes;
	
	@Column(name = "LINK")
	private String link;

	public TedTalk(String title, Instant date, String author, Long views, Long likes, String link, String tedTalkId) {
		super();
		this.title = title;
		this.date = date;
		this.author = author;
		this.views = views;
		this.likes = likes;
		this.link = link;
		this.tedTalkId = tedTalkId;
	}
	
}
