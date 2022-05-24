package com.io.tedtalk.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.io.tedtalk.model.TedTalk;
import com.io.tedtalk.repository.TedTalkRepositoryCustom;

@Repository
public class TedTalkRepositoryImpl implements TedTalkRepositoryCustom {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<TedTalk> findTedTalksByAuthorTitleViewsAndLikes(String author, String title, Long views, Long likes) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TedTalk> cq = cb.createQuery(TedTalk.class);
		Root<TedTalk> tedTalk = cq.from(TedTalk.class);
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(author)) {
			predicates.add(cb.like(tedTalk.get("author"), author + "%"));
		}
		if (StringUtils.isNotBlank(title)) {
			predicates.add(cb.like(tedTalk.get("title"), title + "%"));
		}
		if (views != null) {
			predicates.add(cb.greaterThanOrEqualTo(tedTalk.get("views"), views));
		}
		if (likes != null) {
			predicates.add(cb.greaterThanOrEqualTo(tedTalk.get("likes"), likes));
		}
		cq.where(predicates.toArray(new Predicate[0]));

		return em.createQuery(cq).getResultList();
	}

}
