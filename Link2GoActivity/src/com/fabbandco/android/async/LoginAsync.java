package com.fabbandco.android.async;

import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.fabbandco.android.application.Link2GoApplication;
import com.fabbandco.android.link2go.activity.LoginLink2GoActivity;
import com.fabbandco.android.model.Utilisateur;
import com.fabbandco.android.util.CallRestWeb;
import com.fabbandco.android.util.JSONParser;
import com.fabbandco.android.util.MD5Hash;
import com.fabbandco.android.util.StringUtil;

public class LoginAsync extends AsyncTask<String, String, Utilisateur> {
	LoginLink2GoActivity activity;

	public LoginAsync(LoginLink2GoActivity activity) {
		this.activity = activity;
	}

	protected Utilisateur doInBackground(String... params) {
		String resultWebServ = CallRestWeb.callWebService(((Link2GoApplication)this.activity.getApplication()), "?action=login&login="+params[0]+"&mdp="+MD5Hash.md5Java(params[1]));
		try {
			Logger.getLogger("syso : " + resultWebServ);
			JSONObject jsoObj =  new JSONObject(resultWebServ); 
//			jsoObj = jsoObj.getJSONObject(resultWebServ);
			System.out.println( params[0] + " " +jsoObj.getString("user"));
			if ("ok".equalsIgnoreCase(jsoObj.getString("user"))){
				resultWebServ = CallRestWeb.callWebService(((Link2GoApplication)this.activity.getApplication()), "?action=getuser&login="+params[0]+"&mdp="+MD5Hash.md5Java(params[1]));
//				resultWebServ = resultWebServ.replace("\\", "");
				jsoObj =  new JSONObject(resultWebServ);
				jsoObj = new JSONObject(jsoObj.getString("user"));
				return (Utilisateur) JSONParser.toUtilisateur(jsoObj);
			}
		} catch (Exception e) {
			Logger.getLogger("error : " +e.getMessage());
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
    protected void onPostExecute(Utilisateur user) {
		activity.callBackAsync(user);
    }

}
