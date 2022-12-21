package com.sungmun.NoticeBoard.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class BaseTimeDto {
	public static String toStringLocalDateTime(LocalDateTime dateTime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);//yyyy-MM-dd
		return Optional.ofNullable(dateTime).map(formatter::format).orElse("");
	}

	public static LocalDateTime toLocalDateTimeString(String dateTime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);//yyyy-MM-dd
		return LocalDateTime.parse(dateTime, formatter);
	}
}
