package com.fabbandco.android.api;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fabbandco.android.link2go.R;
import com.fabbandco.android.util.SmsReceiver;
import com.fabbandco.android.util.StringUtil;

public class SecureMessagesActivity extends Activity implements OnClickListener, OnItemClickListener
{
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link2_go);
        
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        registerReceiver(new SmsReceiver(), filter);
        
        this.findViewById( R.id.UpdateList ).setOnClickListener( this );
    }

    ArrayList<String> smsList = new ArrayList<String>();
    
	public void onItemClick( AdapterView<?> parent, View view, int pos, long id ) 
	{
		try 
		{
		    	String[] splitted = smsList.get( pos ).split("\n"); 
			String sender = splitted[0];
			String encryptedData = "";
			for ( int i = 1; i < splitted.length; ++i )
			{
			    encryptedData += splitted[i];
			}
			String data = sender + "\n" + StringUtil.decrypt( new String(SmsReceiver.PASSWORD), encryptedData );
			Toast.makeText( this, data, Toast.LENGTH_SHORT ).show();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void onClick( View v ) 
	{
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query( Uri.parse( "content://sms/inbox" ), null, null, null, null);

		int indexBody = cursor.getColumnIndex( SmsReceiver.BODY );
		int indexAddr = cursor.getColumnIndex( SmsReceiver.ADDRESS );
		
		if ( indexBody < 0 || !cursor.moveToFirst() ) return;
		
		smsList.clear();
		
		do
		{
			String str = "Sender: " + cursor.getString( indexAddr ) + "\n" + cursor.getString( indexBody );
			smsList.add( str );
		}
		while( cursor.moveToNext() );

		
		ListView smsListView = (ListView) findViewById( R.id.SMSList );
		smsListView.setAdapter( new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, smsList) );
		smsListView.setOnItemClickListener( this );
	}
}