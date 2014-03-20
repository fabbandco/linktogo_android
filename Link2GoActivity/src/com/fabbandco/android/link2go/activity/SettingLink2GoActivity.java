package com.fabbandco.android.link2go.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.fabbandco.android.application.PersistanceApplication;
import com.fabbandco.android.link2go.R;
import com.fabbandco.common.Constante;
import com.fabbandco.common.PrivateFabbandcoActivity;

public class SettingLink2GoActivity extends PrivateFabbandcoActivity implements OnClickListener, OnItemClickListener{

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	// Page setting
    	 if (!PersistanceApplication.getInstance().isConnecte()){
	        	Intent i = new Intent(this,LoginLink2GoActivity.class);
	        	startActivity(i);
	        }else{
	        	majView();
	        }
    	viewMessagesErrors();
    }
    
    public void callBackAsync() {
    }
   
    private void majView (){
    	setContentView(R.layout.activity_setting_link2_go);
//    	Toast to = Toast.makeText(this.getApplicationContext(), "Ecran setting", Constante.duration_toast);
//		to.show();
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	}

	@Override
	public void onClick(View v) {
	}
}
