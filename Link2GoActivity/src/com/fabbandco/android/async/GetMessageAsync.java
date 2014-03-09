package com.fabbandco.android.async;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.fabbandco.android.application.PersistanceApplication;
import com.fabbandco.android.util.CallRestWeb;
import com.fabbandco.android.util.SmsReceiver;

public class GetMessageAsync extends AsyncTask<String, String, Boolean> {
	private SmsReceiver smsActivite;

	public GetMessageAsync(final SmsReceiver _smsReceiver) {
		this.smsActivite = _smsReceiver;
	}

	protected Boolean doInBackground(String... params) {
		String resultWebServ = CallRestWeb.callWebService(PersistanceApplication.getInstance().getCurrentApplication(), "?action=getallmessage&login="+PersistanceApplication.getInstance().getUser().getEmail()+"&mdp="+PersistanceApplication.getInstance().getUser().getMdp());
		try {
			JSONObject jsoObj =  new JSONObject(resultWebServ); 
			if (null!=(jsoObj.getString("results"))){
				JSONObject jsoArrayObj =  new JSONObject(jsoObj.getString("results")); 	
				
			}
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
		}
		return false;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
    protected void onPostExecute(final Boolean _isOk) {
		this.smsActivite.callBackAsync(_isOk);
    }

}
