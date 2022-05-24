package com.io.tedtalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.tedtalk.model.TedTalk;

public interface TedTalkRepository extends JpaRepository<TedTalk, String>, TedTalkRepositoryCustom{


}
