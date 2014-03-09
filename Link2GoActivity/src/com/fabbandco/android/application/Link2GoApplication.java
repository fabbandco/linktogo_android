package com.fabbandco.android.application;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Application;

import com.fabbandco.android.util.StorageRequest;

public class Link2GoApplication extends Application {
	//NOT CONNECTED MODE
	private StorageRequest storageRequest;
	private Collection <String> colErrors = new ArrayList<String>();

	@Override
	public void onCreate() {
		super.onCreate();
		initData();
	}
	
	public void catchErrorServer(String str) {
		if (colErrors==null){
			colErrors = new ArrayList<String>();
		}
		colErrors.add(str);
	}

	public StorageRequest getStorageRequest() {
		return storageRequest;
	}

	
	public void initData(){
		storageRequest = new StorageRequest(this);
	}
	
	public Collection getColErrors (){
		return this.colErrors;
	}
	
	public void removeErrors (){
		this.colErrors = new ArrayList<String>();
	}
	
}
