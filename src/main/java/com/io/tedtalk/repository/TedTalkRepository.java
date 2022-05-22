package com.io.tedtalk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.io.tedtalk.model.TedTalk;

public interface TedTalkRepository extends JpaRepository<TedTalk, String> {

	@Query("Select tedTalk from TedTalk tedTalk where (:author is null or author like :author%) and "
			+ "(:title is null or title like :title%) and (:views is null or views >= :views) and (:likes is null or likes >= :likes) order by author asc")
	List<TedTalk> findByAuthorAndTitleAndViewsAndLikes(Optional<String> author, Optional<String> title,
			Optional<Long> views, Optional<Long> likes);

}
