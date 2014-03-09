package com.fabbandco.android.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.fabbandco.android.model.Entity;
import com.fabbandco.android.model.Utilisateur;

public class JSONParser {
	
	public static Entity toEntity(JSONObject jsonObject, Entity entity) throws JSONException, NumberFormatException, IllegalArgumentException, IllegalAccessException{
		@SuppressWarnings("unchecked")
		Iterator<String> iterator = jsonObject.keys();
		while(iterator.hasNext()){
			String key = (String)iterator.next(); 
			Field[]fields = entity.getClass().getDeclaredFields();
			//we range the fields and the key
			for (Field field : fields) {
				field.setAccessible(true);
				if(key.equals(field.getName()) && !jsonObject.isNull(key)){
					//match
					String canonicalName = field.getType().getCanonicalName();
					//convert and parse to string format
					if(int.class.getCanonicalName().equals(canonicalName)){
						field.set(entity, Integer.parseInt(jsonObject.getInt(key)+""));
					}else if(long.class.getCanonicalName().equals(canonicalName)){
						field.set(entity, Long.parseLong(jsonObject.getInt(key)+""));
					}else if(double.class.getCanonicalName().equals(canonicalName)){
						field.set(entity, Double.parseDouble(jsonObject.getDouble(key)+""));
					}else if(boolean.class.getCanonicalName().equals(canonicalName)){
						if(jsonObject.getBoolean(key)) {
							field.set(entity,true);
						}else{
							field.set(entity,false);
						}
					}else if(Date.class.getCanonicalName().equals(canonicalName)){
						field.set(entity, DateUtil.convertToDate(jsonObject.getString(key)+""));
					}else if(Datetime.class.getCanonicalName().equals(canonicalName)){
						field.set(entity, (Datetime)(DateUtil.convertToDateTime(jsonObject.getString(key)+"")));
					}else if(String.class.getCanonicalName().equals(canonicalName)){
						field.set(entity, jsonObject.getString(key)+"");
					}else{
						Log.d("error","Type de donnée non prise en charge");
					}
				}
			}
		}
		return entity;
	}
	
	public static Utilisateur toUtilisateur(JSONObject jsonObject) throws JSONException{
		Utilisateur user = new Utilisateur();
		if(!jsonObject.isNull("id"))
			user.setId(jsonObject.getInt("id"));
		if(!jsonObject.isNull("name"))
			user.setName(jsonObject.getString("name"));
		if(!jsonObject.isNull("firstname"))
			user.setFirst_name(jsonObject.getString("firstname"));
		if(!jsonObject.isNull("email"))
			user.setEmail(jsonObject.getString("email"));
		if(!jsonObject.isNull("phone"))
			user.setPhone(jsonObject.getString("phone"));
		if(!jsonObject.isNull("mdp"))
			user.setMdp(jsonObject.getString("mdp"));
		if(!jsonObject.isNull("type"))
			user.setType(jsonObject.getInt("type"));
		if(!jsonObject.isNull("mdp"))
			user.setMdp(jsonObject.getString("mdp"));
		
		return user;
	}
}
