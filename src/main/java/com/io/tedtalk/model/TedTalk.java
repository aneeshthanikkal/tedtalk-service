package com.io.tedtalk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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
	private String date;
	
	@Column(name = "AUTHOR")
	private String author;
	
	@Column(name = "VIEWS")
	private Long views;
	
	@Column(name = "LIKES")
	private Long likes;
	
	@Column(name = "LINK")
	private String link;

}
