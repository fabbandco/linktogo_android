package com.fabbandco.common;

import java.util.Collection;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.fabbandco.android.application.Link2GoApplication;
import com.fabbandco.android.link2go.R;
import com.google.analytics.tracking.android.EasyTracker;

public class PrivateFabbandcoActivity extends FabbandcoActivity {

	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_link2_go, menu);
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.actionbar_link2_go, menu);
	        return true;
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
