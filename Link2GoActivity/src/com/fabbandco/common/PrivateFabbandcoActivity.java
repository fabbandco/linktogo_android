package com.fabbandco.common;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.fabbandco.android.application.PersistanceApplication;
import com.fabbandco.android.link2go.R;
import com.fabbandco.android.link2go.activity.Link2GoActivity;
import com.fabbandco.android.link2go.activity.LoginLink2GoActivity;
import com.fabbandco.android.link2go.activity.SettingLink2GoActivity;
import com.google.analytics.tracking.android.EasyTracker;

public class PrivateFabbandcoActivity extends FabbandcoActivity {

	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_link2_go, menu);
	        menu.findItem(R.id.menu_home).setIntent(onClickHome());
	        menu.findItem(R.id.menu_settings).setIntent(onClickSetting());
	        menu.findItem(R.id.menu_quit).setIntent(onClickQuit());
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.actionbar_link2_go, menu);
	        return true;
	    }
	 
	 public void onClickCloud (View v){
		Intent i = new Intent(this,Link2GoActivity.class);
	    startActivity(i);
	 }
	 
	 
	 public Intent onClickHome (){
			return new Intent(this,Link2GoActivity.class);
		 }
	 
	 public Intent onClickSetting (){
		return new Intent(this,SettingLink2GoActivity.class);
	 }
	 
	 public Intent onClickQuit (){
		 PersistanceApplication.getInstance().setConnecte(false);
		 PersistanceApplication.getInstance().setUser(null);
		 return  new Intent(this,LoginLink2GoActivity.class);
	 }
	 
	  @Override
	  public void onStart() {
	    super.onStart();
	    EasyTracker.getInstance().activityStart(this); // Add this method.
	  }

	  @Override
	  public void onStop() {
	    super.onStop();
	    EasyTracker.getInstance().activityStop(this); // Add this method.
	  }
}
