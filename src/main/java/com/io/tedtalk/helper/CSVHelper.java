package com.io.tedtalk.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;
import com.io.tedtalk.constants.TedTalkConstants;
import com.io.tedtalk.model.TedTalk;

public class CSVHelper {
	public static String TYPE = "text/csv";

//  static String[] HEADERs = { "Title", "Author", "Views", "Likes", "Link" };
	public static boolean hasCSVFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<TedTalk> csvToTedtalk(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			List<TedTalk> tedTalks = new ArrayList<TedTalk>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			Long likes=(long) 0;
			Long views=(long) 0;
			for (CSVRecord csvRecord : csvRecords) {
				likes = StringUtils.isNumeric(csvRecord.get(TedTalkConstants.LIKES))?Long.parseLong(csvRecord.get(TedTalkConstants.LIKES)):0;
				views = StringUtils.isNumeric(csvRecord.get(TedTalkConstants.VIEWS))?Long.parseLong(csvRecord.get(TedTalkConstants.VIEWS)):0;
				TedTalk tedTalk = new TedTalk(csvRecord.get(TedTalkConstants.TITLE), csvRecord.get(TedTalkConstants.DATE),
						csvRecord.get(TedTalkConstants.AUTHOR), views,
						likes, csvRecord.get(TedTalkConstants.LINK),
						Generators.timeBasedGenerator().generate().toString());
				tedTalks.add(tedTalk);
			}
			return tedTalks;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
}