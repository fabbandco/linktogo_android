package com.fabbandco.android.link2go.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fabbandco.android.application.ActivityStackApplication;
import com.fabbandco.android.application.Link2GoApplication;
import com.fabbandco.android.application.PersistanceApplication;
import com.fabbandco.android.async.LoginAsync;
import com.fabbandco.android.link2go.R;
import com.fabbandco.android.model.Utilisateur;
import com.fabbandco.android.util.StringUtil;
import com.fabbandco.common.Constante;
import com.fabbandco.common.PublicFabbandcoActivity;

public class LoginLink2GoActivity extends PublicFabbandcoActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewMessagesErrors();
        PersistanceApplication.getInstance().setCurrentApplication((Link2GoApplication)this.getApplication());
        setContentView(R.layout.login_link2_go);
    }
    
    public void tryConnect(View view){
    	PersistanceApplication.getInstance().setConnecte(true);
    	TextView login = (TextView)findViewById(R.id.login);
    	TextView mdp = (TextView)findViewById(R.id.mdp);
    	if(!StringUtil.isEmpty(login.getText()+"") && !StringUtil.isEmpty(mdp.getText()+"")){
    		LoginAsync loginAsc = new LoginAsync(this);
    		loginAsc.execute(login.getText()+"",mdp.getText()+"");
    	}
    }
    
    public void goForgotPassword(View view){
    	Intent i = new Intent(this,MdpLostLink2GoActivity.class);
    	startActivity(i);
    }
    
    public void goInscription(View view){
    	Toast message = Toast.makeText(this.getApplicationContext(), "Inscription en ligne \n Redirection en cours", Constante.duration_toast);
    	message.show();
    	Uri uri = Uri.parse(Constante.url_new);
    	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    	startActivity(intent);
    }

	public void callBackAsync(Utilisateur user) {
		if (user==null){
			Toast message = Toast.makeText(this.getApplicationContext(), R.string.label_pb_connection_login, Constante.duration_toast);
	    	message.show();
		}else{
			PersistanceApplication.getInstance().setConnecte(true);
			PersistanceApplication.getInstance().setUser(user);
			Intent i = new Intent(this,Link2GoActivity.class);
			startActivity(i);
		}
	} 
}
