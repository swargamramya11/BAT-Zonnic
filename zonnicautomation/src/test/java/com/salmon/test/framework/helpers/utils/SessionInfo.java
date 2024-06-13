package com.salmon.test.framework.helpers.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SessionInfo {
	public static String siteIdentifier;
	public static String scenarioName;
	public static String scenarioId;
	public static final String startTime;

	static
	{
		final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		final Date date = new Date();
		startTime = dateFormat.format(date);
	}
}
