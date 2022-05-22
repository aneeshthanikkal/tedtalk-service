package com.io.tedtalk.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;
import com.io.tedtalk.constants.TedTalkConstants;
import com.io.tedtalk.model.TedTalk;

@Component
public class CSVHelper {
	public static String TYPE = "text/csv";

	public boolean hasCSVFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public List<TedTalk> mapCsvToTedtalk(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			return csvParser.getRecords().stream().map(mapCvsRecord).collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	private Function<CSVRecord, TedTalk> mapCvsRecord = (csvRecord) -> {

		return TedTalk.builder().author(csvRecord.get(TedTalkConstants.AUTHOR))
				.date(csvRecord.get(TedTalkConstants.DATE))
				.likes(StringUtils.isNumeric(csvRecord.get(TedTalkConstants.LIKES))
						? Long.parseLong(csvRecord.get(TedTalkConstants.LIKES))
						: 0)
				.link(csvRecord.get(TedTalkConstants.LINK)).title(csvRecord.get(TedTalkConstants.TITLE))
				.views(StringUtils.isNumeric(csvRecord.get(TedTalkConstants.VIEWS))
						? Long.parseLong(csvRecord.get(TedTalkConstants.VIEWS))
						: 0).tedTalkId(Generators.timeBasedGenerator().generate().toString())
				.build();
		
		};

}