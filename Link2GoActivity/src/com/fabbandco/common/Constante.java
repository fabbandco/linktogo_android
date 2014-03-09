package com.fabbandco.common;

public interface Constante {

	public static int TIME_TO_LEAVE_LOGIN = 2; // en jour
	public static int duration_toast = 2;
	
	
	public static final String url = "http://web.linktogo.fr/linktogoWeb";
	public static final String url_new = "http://web.linktogo.fr/linktogoWeb/new-user";
	public static final String urlApi = url+"/webserve";
	//time for time out
	public static final int timeOutConnection = 20000;
	public static final int timeOutSocket = 20000;
	
	//number of element to be displayed on the map
	public static final int LIMIT_PIN_MAP = 29;
	
	//lenght of the string will be splited be StringUtil.isNotNullShort() method
	public static final int LIMIT_SPLIT_STRING = 100;
	
	//pattern for Date and Datetime class
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";
	public static final String DATE_TIME_FULL_PATTERN = "dd/MM/yyyy HH:mm:ss";
	public static final String DATE_PATTERN_SERVER = "yyyy-MM-dd HH:mm:ss";
	
}
