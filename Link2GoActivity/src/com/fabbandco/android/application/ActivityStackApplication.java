package com.fabbandco.android.application;

import java.util.Hashtable;

import android.app.Activity;

import com.fabbandco.android.model.Utilisateur;


public class ActivityStackApplication {

	static private ActivityStackApplication _instance;
	private Hashtable <Class ,Activity> hasActivity = null;
	

	static public ActivityStackApplication getInstance() {
		if (_instance == null) {
			_instance = new ActivityStackApplication();
		}
		return _instance;
	}
	
	
//	static public Activity addHashActivity (Class classT , Activity activity){
//		if (getInstance().hasActivity.get(classT)!=null){
//			return getInstance().hasActivity.get(classT);
//		}
//		
//		getInstance().hasActivity.put(classT, activity);
//		return
//	}
}
