package com.fabbandco.android.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

import com.fabbandco.common.Constante;

public class StringUtil {
	
	private static final String CIPHER_ALGORITHM = "AES";
	private static final String RANDOM_GENERATOR_ALGORITHM = "SHA1PRNG";
	private static final int RANDOM_KEY_SIZE = 128;
	
	//this method return an empty string if the argument is null or something like that
	//and return the string if isn't a null string
	public static String isNotNull(String chaine){
		if(chaine==null || "NULL".equals(chaine) || "null".equals(chaine) || " ".equals(chaine) || "Null".equals(chaine)){
			return "";
		}
		return chaine;
	}

	//this method return an empty string if the argument is null or something like that
	//and return the string if isn't a null string
	//the return element splited if is too long
	public static String isNotNullShort(String chaine){
		if("NULL".equals(chaine) || "null".equals(chaine) || " ".equals(chaine) || "Null".equals(chaine)){
			return "";
		}
		
		//and we split the string
		if(chaine.length()>Constante.LIMIT_SPLIT_STRING){
			return chaine.substring(0,Constante.LIMIT_SPLIT_STRING);
		}
		return chaine;
	}
	
	//this method test if the string is null or empty
	public static boolean isEmpty(String chaine){
		if("".equals(chaine) || " ".equals(chaine) || "null".equals(chaine) || "NULL".equals(chaine) || "Null".equals(chaine)){
			return true;
		}
		return false;
	}
	
	//this method return a string null if the argument is like null
	public static String convertToNull(String chaine){
		if("".equals(chaine) || " ".equals(chaine) || "null".equals(chaine) || "NULL".equals(chaine) || "Null".equals(chaine)){
			return null;
		}
		return chaine;
	}
	
	public static String encrypt( String password, String data ) throws Exception 
	{
		
		byte[] secretKey = generateKey( password.getBytes() );
	    byte[] clear = data.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec( secretKey, CIPHER_ALGORITHM );
		Cipher cipher = Cipher.getInstance( CIPHER_ALGORITHM );
	    cipher.init( Cipher.ENCRYPT_MODE, secretKeySpec );
	    byte[] encrypted = cipher.doFinal( clear );
	    String encryptedString = Base64.encodeToString( encrypted, Base64.DEFAULT );	    
		return encryptedString;
	}
	
	// Decrypts string encoded in Base64
	public static String decrypt(String password, String encryptedData)
			throws Exception {
		byte[] secretKey = generateKey(password.getBytes());
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey,CIPHER_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] encrypted = Base64.decode(encryptedData, Base64.DEFAULT);
		byte[] decrypted = cipher.doFinal(encrypted);
		return new String(decrypted);
	}
	
	public static byte[] generateKey( byte[] seed ) throws Exception
	{
		KeyGenerator keyGenerator = KeyGenerator.getInstance(CIPHER_ALGORITHM);
		SecureRandom secureRandom = SecureRandom.getInstance(RANDOM_GENERATOR_ALGORITHM);
		secureRandom.setSeed(seed);
		keyGenerator.init(RANDOM_KEY_SIZE, secureRandom);
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey.getEncoded();
	}
}
