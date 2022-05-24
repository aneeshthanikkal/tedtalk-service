package com.io.tedtalk.repository;

import java.util.List;

import com.io.tedtalk.model.TedTalk;

public interface TedTalkRepositoryCustom {

    List<TedTalk> findTedTalksByAuthorTitleViewsAndLikes(String author, String title,
			Long views, Long likes);

}
