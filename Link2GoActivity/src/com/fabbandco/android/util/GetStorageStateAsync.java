package com.fabbandco.android.util;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;

import android.os.AsyncTask;

import com.fabbandco.android.application.Link2GoApplication;

public class GetStorageStateAsync extends AsyncTask<Link2GoApplication, Integer, Void>{

	@Override
	protected Void doInBackground(Link2GoApplication... params) {
		Link2GoApplication application = params[0];

		try {
			FileInputStream fichier = application.openFileInput("storage.ser");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			@SuppressWarnings("unchecked")
			Hashtable<String, String> hashTable = (Hashtable<String, String>) ois.readObject();
			application.getStorageRequest().setHashTable(hashTable);
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
