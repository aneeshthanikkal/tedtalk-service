package com.io.tedtalk.repository;

import java.time.Instant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.io.tedtalk.model.TedTalk;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TedTalkDaoTest {

	@Autowired
	TedTalkRepository tedTalkRepository;

	@Test
	public void testCreateReadDelete() {

		TedTalk tedTalk = new TedTalk("Title", Instant.now(), "Author", 1l, 1l, "link", "id");

		tedTalkRepository.save(tedTalk);

		Iterable<TedTalk> tedTalks = tedTalkRepository.findAll();
		Assertions.assertThat(tedTalks).extracting(TedTalk::getTitle).containsOnly("Title");

		tedTalkRepository.deleteAll();
		Assertions.assertThat(tedTalkRepository.findAll()).isEmpty();
	}
}