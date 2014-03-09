package com.fabbandco.android.link2go.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fabbandco.android.application.PersistanceApplication;
import com.fabbandco.android.link2go.R;
import com.fabbandco.common.Constante;
import com.fabbandco.common.PrivateFabbandcoActivity;

public class MdpLostLink2GoActivity extends PrivateFabbandcoActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewMessagesErrors();
        setContentView(R.layout.mdp_lost_link2_go);
        
    }
    
    public void sendMdpLost(View view){
    	Context context = this.getApplicationContext();
    	Toast toast = Toast.makeText(context, R.string.label_requete_envoye, Constante.duration_toast);
    	toast.show();
    	Intent i = new Intent(this,Link2GoActivity.class);
    	startActivity(i);
    	PersistanceApplication.getInstance().setConnecte(false);
    	PersistanceApplication.getInstance().setUser(null);
    }
}
