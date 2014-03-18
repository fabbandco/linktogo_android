package com.fabbandco.android.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import android.util.Log;

import com.fabbandco.common.Constante;

public class DateUtil {
	
	static SimpleDateFormat spdate = new SimpleDateFormat(Constante.DATE_PATTERN_SERVER);
	static SimpleDateFormat sp = new SimpleDateFormat(Constante.DATE_PATTERN);
	static SimpleDateFormat spFull = new SimpleDateFormat(Constante.DATE_TIME_PATTERN);
	public static String convertToString(Date event){
		return sp.format(event);
	}
	public static String convertToStringWithTime(Date event){
		return spFull.format(event);
	}
	
	public static String convertToFullString(Date event){
		return spFull.format(event);
		
	}
	
	public static Datetime convertToDateTime(String date){
		try {
			return new Datetime(spFull.parse(date));
		} catch (ParseException e) {
			Log.d("error","Format de date incorrect");
			return null;
		}
	}
	
	public static Date convertToDate(String date){
		try {
			return sp.parse(date);
		} catch (ParseException e) {
			Log.d("error","Format de date incorrect");
			return null;
		}
	}
	
	public static Date convertToDateServer(String date){
		try {
			return spdate.parse(date);
		} catch (ParseException e) {
			Log.d("error","Format de date incorrect");
			return null;
		}
	}
	public static String convertToStringDifDate(Date event){
		//pour une comparaison avec la date d'aujourd'hui
		return convertToStringDifDate(event, new Date());
	}
	
	//this method can convert to Date into a string of type "il y a xx temps"
	public static String convertToStringDifDate(Date event, Date compareTo){
		//on converie la difference entre les deux dates en un temps 
		// il y a 1seconde
		// il y a 1 minute
		// il y a 1 heure
		// il y a 1 jour
		// il y a 1 mois
		// il y a 1 an
		
		//on set un calendar avec la difference de temps entre les deux
		long time = compareTo.getTime()-event.getTime();
		
		if(time/(1000*60*60*24*30*12)>=1){
			//il y a plus d'un an
			if(time/(1000*60*60*24*30*12)>=2){
				return time/(1000*60*60*24*30*12)+" ans";
			}else{
				return time/(1000*60*60*24*30*12)+" an";
			}
			
			//TODO nombre de jour par mois
		}else if(time/(1000*60*60*24*30)>=1){
			//il y a plus d'un mois
			return time/(1000*60*60*24*30)+" mois";
			
		}else if(time/(1000*60*60*24)>=1){
			//il y a plus d'un jour
			if(time/(1000*60*60*24)>=2){
				return time/(1000*60*60*24)+" jours";
			}else{
				return time/(1000*60*60*24)+" jour";
			}
			
		}else if(time/(1000*60*60)>=1){
			//il y a plus d'une heure
			if(time/(1000*60*60)>=2){
				return time/(1000*60*60)+" heures";
			}else{
				return time/(1000*60*60)+" heure";
			}
			
		}else if(time/(1000*60)>=1){
			//il y a plus d'une minute
			if(time/(1000*60)>=2){
				return time/(1000*60)+" minutes";
			}else{
				return time/(1000*60)+" minute";
			}
			
		}else if(time/(1000)>=1){
			//il y a plus d'une seconde
			if(time/(1000)>=2){
				return time/(1000)+" secondes";
			}else{
				return time/(1000)+" seconde";
			}
		}else {
			return "quelques secondes";
		}
	}
	
	public static String convertToStringHour(Date event){
		if(event!=null){
			DecimalFormat dFormat = new DecimalFormat("00");
			GregorianCalendar calEvent = new GregorianCalendar();
			calEvent.setTime(event);
			
			GregorianCalendar calNow = new GregorianCalendar();
			calNow.setTime(new Date());
			
			if(calEvent.get(GregorianCalendar.YEAR)==calNow.get(GregorianCalendar.YEAR) && calEvent.get(GregorianCalendar.MONTH)==calNow.get(GregorianCalendar.MONTH) && calEvent.get(GregorianCalendar.DAY_OF_MONTH)==calNow.get(GregorianCalendar.DAY_OF_MONTH)){
				//it's today
				return calEvent.get(GregorianCalendar.HOUR_OF_DAY)+"h"+dFormat.format(calEvent.get(GregorianCalendar.MINUTE));
			}else{
				return calEvent.get(GregorianCalendar.DAY_OF_MONTH)+"/"+calEvent.get(GregorianCalendar.MONTH)+"/"+calEvent.get(GregorianCalendar.DAY_OF_MONTH);
			}
		}return null;
	}
	
	public static String formatDateWithSecond(Date date) throws ParseException {
		SimpleDateFormat spf = new SimpleDateFormat("yyyyMMddHHmmss");
		return spf.format(date);
	}
	
}
