package com.fabbandco.android.util;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class MD5Hash {
	
	private static final String CIPHER_ALGORITHM = "AES";
	private static final String RANDOM_GENERATOR_ALGORITHM = "SHA1PRNG";
	private static final int RANDOM_KEY_SIZE = 128;
	public static byte[] PASSWORD = new byte[]{ (byte)0x20, (byte)0x32,(byte) 0x34, (byte)0x47, (byte) 0x84,(byte) 0x33,(byte) 0x58,(byte)0x64,(byte)0x65,(byte)0x84 };
	
	public static String md5Java(String message){
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
           
            //converting byte array to Hexadecimal String
           StringBuilder sb = new StringBuilder(2*hash.length);
           for(byte b : hash){
               sb.append(String.format("%02x", b&0xff));
           }
          
           digest = sb.toString();
          
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StringReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StringReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }
	
	public static String encrypt (final String _data) throws Exception{
		return encrypt(new String(PASSWORD), _data);
	}
	
	public static String encrypt( String password, String data ) throws Exception 
	{		
		byte[] secretKey = generateKey( password.getBytes() );
	    byte[] clear = data.getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec( secretKey, CIPHER_ALGORITHM );
		Cipher cipher = Cipher.getInstance( CIPHER_ALGORITHM );
	    cipher.init( Cipher.ENCRYPT_MODE, secretKeySpec );
	    byte[] encrypted = cipher.doFinal( clear );
	    byte[] encryptedString = Base64.encode(encrypted, Base64.DEFAULT);
		return encryptedString.toString();
	}
	
	// Decrypts string encoded in Base64
	public static String decrypt(String password, String encryptedData)	throws Exception {
		byte[] secretKey = generateKey(password.getBytes());
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey,CIPHER_ALGORITHM);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] encrypted = Base64.decode(encryptedData, RANDOM_KEY_SIZE);
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
	
	public static String getKey (){
		return new String (PASSWORD);
	}
	
	public static void setKey (String _key){
		PASSWORD = _key.getBytes();
	}
}
