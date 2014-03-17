package com.fabbandco.android.util;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.fabbandco.android.application.Link2GoApplication;
import com.fabbandco.android.link2go.R;
import com.fabbandco.common.Constante;

//this class is used for to do the request to the webserver 
public class CallRestWeb {
	
	//this method send and rceived the information to/from the serveur
	//it include the an offline system 
	//TODO
	public synchronized static String callWebService(Link2GoApplication application, String url) {
		//we test the connectivity
		if(isOnline(application)){
			try {
				String result=null;

				HttpParams httpParameters = new BasicHttpParams();
				// Set the timeout in milliseconds until a connection is established.
				// The default value is zero, that means the timeout is not used. 
				int timeoutConnection = Constante.timeOutConnection;
				HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
				// Set the default socket timeout (SO_TIMEOUT) 
				// in milliseconds which is the timeout for waiting for data.
				int timeoutSocket = Constante.timeOutSocket;
				HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		    	HttpClient httpclient = new DefaultHttpClient(httpParameters);
		    	//we do the request after encode in UTF8
		    	HttpGet request = new HttpGet(Constante.urlApi+url);
//		        request.setHeader("Content-type", "application/json");

		        ResponseHandler<String> handler = new BasicResponseHandler();
	    		result = httpclient.execute(request, handler);
	    		httpclient.getConnectionManager().shutdown();
	    		result = isValid(result);
	    		if(result==null){
	    			//probleme JSON
	    			application.catchErrorServer("");
					return null;
	    		}
	    		
	    		//we save the response
//	    		application.getStorageRequest().putResponse(url, result);
	    		//we serialize this result
//	    		SaveStorageStateAsync async = new SaveStorageStateAsync();
//	    		async.execute(application);
	    		return result;
			} catch (ConnectTimeoutException e) {
				Log.d("pb-connect time out",e+"");
				application.catchErrorServer(application.getResources().getString(R.string.timeout_error)+" ("+e.getMessage()+")");
				return null;
			} catch (ClientProtocolException e) {
				Log.d("pb-ClientProtocolException",e+"");
				application.catchErrorServer(application.getResources().getString(R.string.server_error)+" ("+e.getMessage()+")");
				return null;
			} catch (IOException e) {
				Log.d("pb-IOException",e+"");
				application.catchErrorServer(application.getResources().getString(R.string.not_found_error)+" ("+e.getMessage()+")");
				return null;
			} catch (JSONException e) {
				Log.d("pb-JSONException",e+"");
				application.catchErrorServer(application.getResources().getString(R.string.json_error)+" ("+e.getMessage()+")");
				return null;
			} catch (Exception e) {
				Log.d("pb-Unknow",e+"");
				application.catchErrorServer(application.getResources().getString(R.string.server_error)+" ("+e.getMessage()+")");
				return null;
			}
		}else{
			Log.d("No Exception","Not connected");
			//for the demo we use the not connected mode
			String response = application.getStorageRequest().getResponse(url); 
			if(response!=null){
				return response;
			}else{
				application.catchErrorServer(application.getResources().getString(R.string.no_data_error)+"");
			}
			return null;
		}
    }
	
	
	//this method test if the application have connectivity
	public static boolean isOnline(Link2GoApplication application) {
	    ConnectivityManager cm = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
	    return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	
	//this method check if the received flux is correct
	public static String isValid(String result) throws JSONException{
		//we test if status is okay for the flux
		JSONObject jsonObject = new JSONObject (result);
		//and we return the result
		if("ok".equals(jsonObject.getString("status"))){
			//everything is cool
			
			//we test if the result is a JSON array or a JSON object or a string
			
			JSONObject jsonObj = jsonObject.optJSONObject("results");
			if(jsonObj!=null) {
				return jsonObject.getJSONObject("results").toString();
			}else{
				JSONArray jsonArray = jsonObject.optJSONArray("results");
				if(jsonArray!=null){
					return jsonObject.getJSONArray("results").toString();
				}else{
					return (jsonObject.getString("results")).toString();
				}
			}
		}else{
			//error
			return null;
		}
	}
}
