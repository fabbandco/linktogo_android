package com.fabbandco.android.async;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.fabbandco.android.application.PersistanceApplication;
import com.fabbandco.android.util.CallRestWeb;
import com.fabbandco.android.util.SmsReceiver;

public class AddMessageAsync extends AsyncTask<String, String, Boolean> {
	private SmsReceiver smsActivite;

	public AddMessageAsync(final SmsReceiver _smsReceiver) {
		this.smsActivite = _smsReceiver;
	}

	protected Boolean doInBackground(String... params) {
		String resultWebServ = CallRestWeb.callWebService(PersistanceApplication.getInstance().getCurrentApplication(), "?action=addmessage&login="+PersistanceApplication.getInstance().getUser().getEmail()+"&mdp="+PersistanceApplication.getInstance().getUser().getMdp()
				+"&messagekey=" + params[0]
				+"&message=" + params[1]
				+"&number="+ params[2]
				+"&name="+ params[3]
				+"&date="+ params[4]);
		try {
			//Logger.getLogger("syso : " + resultWebServ);
			JSONObject jsoObj =  new JSONObject(resultWebServ); 
			if ("ok".equalsIgnoreCase(jsoObj.getString("results"))){
				return true;
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
