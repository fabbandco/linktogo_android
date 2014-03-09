package com.fabbandco.android.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.fabbandco.android.application.PersistanceApplication;
import com.fabbandco.android.async.AddMessageAsync;
import com.fabbandco.android.link2go.R;
import com.fabbandco.android.model.SmsMessageCrypt;
import com.fabbandco.common.Constante;

public class SmsReceiver extends BroadcastReceiver 
{
	
	private Collection<SmsMessageCrypt> colSmsMessageCrypts = new ArrayList<SmsMessageCrypt>();
	
	public static final String SMS_EXTRA_NAME = "pdus";
	public static final String SMS_URI = "content://sms";
	
	public static final String ADDRESS = "address";
    public static final String PERSON = "person";
    public static final String DATE = "date";
    public static final String READ = "read";
    public static final String STATUS = "status";
    public static final String TYPE = "type";
    public static final String BODY = "body";
    public static final String SEEN = "seen";
    
    public static final int MESSAGE_TYPE_INBOX = 1;
    public static final int MESSAGE_TYPE_SENT = 2;
    
    public static final int MESSAGE_IS_NOT_READ = 0;
    public static final int MESSAGE_IS_READ = 1;
    
    public static final int MESSAGE_IS_NOT_SEEN = 0;
    public static final int MESSAGE_IS_SEEN = 1;
	
    // Change the password here or give a user possibility to change it
    public static final byte[] PASSWORD = new byte[]{ 0x20, 0x32, 0x34, 0x47, (byte) 0x84, 0x33, 0x58 };
    
	public void onReceive(Context context, Intent intent ) 
	{
		// Get SMS map from Intent
        Bundle extras = intent.getExtras();
        Date now = new Date ();
        String pass = MD5Hash.md5Java(now.getTime()+"");
        if (PersistanceApplication.getInstance().isConnecte() ){
        	pass = MD5Hash.md5Java(PersistanceApplication.getInstance().getUser().getMdp()+now.getTime());
        }
        String messages = "";
        
        if ( extras != null )
        {
            Object[] smsExtra = (Object[]) extras.get( SMS_EXTRA_NAME );
            ContentResolver contentResolver = context.getContentResolver();
            SmsMessage smExt = null;
            for ( int i = 0; i < smsExtra.length; ++i )
            {
            	SmsMessage sms = SmsMessage.createFromPdu((byte[])smsExtra[i]);
            	if(i==0)smExt = sms; 
            	messages +=  sms.getMessageBody().toString();
            }
            
            String encryptedPassword = "";
            encryptedPassword = URLEncoder.encode(messages+"");
            if (PersistanceApplication.getInstance().isConnecte() && smExt!=null){
            	SmsMessageCrypt smsCrypt = new SmsMessageCrypt(smExt, false, pass,encryptedPassword, contentResolver);
            	smsCrypt.setOk(true);
        		this.colSmsMessageCrypts.add(smsCrypt);
        	}else{
        		SmsMessageCrypt smsCrypt = new SmsMessageCrypt(smExt, false, pass, encryptedPassword, contentResolver);
            	smsCrypt.setOk(false);
        		this.colSmsMessageCrypts.add(smsCrypt);
        	}
            
            // Envoi des Messages 
            if (PersistanceApplication.getInstance().isConnecte() && smExt!=null){
            	for (SmsMessageCrypt object : this.colSmsMessageCrypts) {
            		AddMessageAsync addMess = new AddMessageAsync(this);
            		addMess.execute(URLEncoder.encode(object.getStrPass()+""),object.getStrCrypte(),URLEncoder.encode(object.getSms().getOriginatingAddress()+""),URLEncoder.encode(object.getSms().getOriginatingAddress()+""));
				}
        	}
         }
	}
	
	
	
	public void callBackAsync (final Boolean _isok){
			if ( _isok){
				Toast to = Toast.makeText(PersistanceApplication.getInstance().getCurrentApplication(), R.string.label_message_save, Constante.duration_toast);
        		to.show();
			}else{
				Toast to = Toast.makeText(PersistanceApplication.getInstance().getCurrentApplication(), R.string.label_message_not_save, Constante.duration_toast);
        		to.show();
			}
	}
	
	 /***********************************************************************************************
	  * Private
	  **********************************************************************************************/
	
	private void putSmsToDatabase(final ContentResolver contentResolver, final SmsMessage sms, final String encryptedPassword )
	{
		ContentValues values = new ContentValues();
        values.put( ADDRESS, sms.getOriginatingAddress() );
        values.put( DATE, sms.getTimestampMillis());
        values.put( READ, MESSAGE_IS_NOT_READ ); 
        values.put( STATUS, sms.getStatus() );
        values.put( TYPE, MESSAGE_TYPE_INBOX );
        values.put( SEEN, MESSAGE_IS_NOT_SEEN );
       	values.put( BODY, encryptedPassword );
        contentResolver.insert(Uri.parse( SMS_URI ), values );
	}
}
