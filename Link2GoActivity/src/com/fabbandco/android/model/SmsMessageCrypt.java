package com.fabbandco.android.model;

import android.content.ContentResolver;
import android.telephony.SmsMessage;

public class SmsMessageCrypt extends Entity {

		private SmsMessage sms;
		private boolean isOk = false;
		private String strPass = "";
		private String strCrypte = "";
		private ContentResolver contentResolver;
		
		
		public SmsMessageCrypt(final SmsMessage sms,final  boolean isOk,final String pass ,final  String strCrypte,final  ContentResolver contentResolver) {
			super();
			this.sms = sms;
			this.isOk = isOk;
			this.strPass = pass;
			this.strCrypte = strCrypte;
			this.contentResolver = contentResolver;
		}

		/*****GETSETTER***********************************************************************/
		
		public SmsMessage getSms() {
			return sms;
		}


		public void setSms(SmsMessage sms) {
			this.sms = sms;
		}


		public boolean isOk() {
			return isOk;
		}


		public void setOk(boolean isOk) {
			this.isOk = isOk;
		}


		public String getStrCrypte() {
			return strCrypte;
		}


		public void setStrCrypte(String strCrypte) {
			this.strCrypte = strCrypte;
		}


		public ContentResolver getContentResolver() {
			return contentResolver;
		}
		
		public void setContentResolver(ContentResolver contentResolver) {
			this.contentResolver = contentResolver;
		}

		public String getStrPass() {
			return strPass;
		}

		public void setStrPass(String strPass) {
			this.strPass = strPass;
		}

}
