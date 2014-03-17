package com.fabbandco.android.application;

import java.util.Hashtable;

import android.app.Activity;

import com.fabbandco.android.model.Utilisateur;


public class ActivityStackApplication {

	static private ActivityStackApplication _instance;
	private Hashtable <String ,Activity> hasActivity = null;
	

	static public ActivityStackApplication getInstance() {
		if (_instance == null) {
			_instance = new ActivityStackApplication();
		}
		return _instance;
	}
	
	
	static public void addHashActivity (final String nomActivity,final Activity activity){
		ActivityStackApplication app = getInstance();
		if (app.hasActivity.get(nomActivity)==null){
			getInstance().hasActivity.put(nomActivity, activity);
		}
	}
}
