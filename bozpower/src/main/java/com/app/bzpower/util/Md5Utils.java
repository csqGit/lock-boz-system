package com.app.bzpower.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 加密生成器
 * @author 123
 *
 */
@SuppressWarnings("restriction")
public class Md5Utils {

	public static String encodingMd5(String str) {
		//确定计算方法
		MessageDigest md5;
		String newStr = "";
		try {
			md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder  encoder = new BASE64Encoder();
			newStr = encoder.encode(md5.digest(str.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newStr;
	}
	
	public static boolean checkPassword(String newPass, String oldPass) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		if(encodingMd5(newPass).equals(oldPass)) {
			return true;
		}else 
			return false;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String str = encodingMd5("admin1");
		System.out.println(str);
//		boolean result = checkPassword("1","xMpCOKC5I4INzFCab3WEmw==" );
//		System.out.println(result);
		
//		System.out.println(convertMD5("1"));
		
	}
	
	 public static String convertMD5(String inStr){  
		  
	        char[] a = inStr.toCharArray();  
	        for (int i = 0; i < a.length; i++){  
	            a[i] = (char) (a[i] ^ 't');  
	        }  
	        String s = new String(a);  
	        return s;  
	  
	    }  
	 
	 
	
}
