package com.fabbandco.android.application;

import java.util.Hashtable;

import android.app.Activity;
import android.content.Intent;

import com.fabbandco.android.link2go.activity.Link2GoActivity;
import com.fabbandco.android.link2go.activity.LogOutLink2GoActivity;
import com.fabbandco.android.link2go.activity.LoginLink2GoActivity;
import com.fabbandco.android.link2go.activity.MdpLostLink2GoActivity;
import com.fabbandco.android.link2go.activity.SettingLink2GoActivity;


public class ActivityStackApplication {

	static private ActivityStackApplication _instance;
	private Hashtable <String ,Activity> hasActivity = null;
	

	static public ActivityStackApplication getInstance() {
		if (_instance == null) {
			_instance = new ActivityStackApplication();
		}
		return _instance;
	}
	
	
	static public void liveCycle (final String nomActivity , final Activity _act){
		ActivityStackApplication app = getInstance();
		if (app.hasActivity.get(nomActivity)!=null){
			Activity activity = app.hasActivity.get(nomActivity);
			activity.recreate();
		}else{
			Intent i = new Intent(_act,LoginLink2GoActivity.class);
        	if ("Link2GoActivity".equals(nomActivity)){
        		i = new Intent(_act,Link2GoActivity.class);
			}else if ("LoginLink2GoActivity".equals(nomActivity)) {
				i = new Intent(_act,LoginLink2GoActivity.class);
			}else if ("MdpLostLink2GoActivity".equals(nomActivity)) {
				i = new Intent(_act,MdpLostLink2GoActivity.class);
			}else if ("LoginLOutk2GoActivity".equals(nomActivity)) {
				i = new Intent(_act,LogOutLink2GoActivity.class);
			}else if ("SettingLink2GoActivity".equals(nomActivity)) {
				i = new Intent(_act,SettingLink2GoActivity.class);
			}
			_act.startActivity(i);
		}
	}
	
	
	static public void addHashActivity (final String nomActivity,final Activity activity){
		ActivityStackApplication app = getInstance();
		if (app.hasActivity.get(nomActivity)==null){
			getInstance().hasActivity.put(nomActivity, activity);
		}
	}
}
