package com.echo.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class EncodeUtils {
	
	/**
	 * 用于用户加密时的盐
	 */
	public static String getSalt(int length){
		char[] chars = "0123456789abcdefghijklmnopqrwtuvzxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		char[] saltchars = new char[length];
		Random RANDOM = new SecureRandom();
		for(int i = 0; i < length; i++){
			int n = RANDOM.nextInt(62);
			saltchars[i] = chars[n];
		}
		String salt = new String(saltchars);
		return salt;
	}
	
	/* 
     * 不可逆算法  MD5 
     */  
    public static String Md5(String target){  
        return DigestUtils.md5Hex(target);  
    }  
    
    /* 
     * 不可逆算法  SHA1(安全哈希算法)
     */  
    public static String SHA1Encode(String target){  
        String str = DigestUtils.sha1Hex(target);  
//      str = DigestUtils.sha256Hex(target);  
//      str = DigestUtils.sha384Hex(target);  
//      str = DigestUtils.sha512Hex(target);  
        return str;
    }  
      
    /* 
     * 可逆算法  BASE64 加密
     */  
    public static String Base64Endode(String target){  
        byte[] byteArray = Base64.encodeBase64(target.getBytes(), true);  
        String str = new String(byteArray);  
        return str;
    } 
    
    public static String Base64Endode(byte[] target){  
        byte[] byteArray = Base64.encodeBase64(target, true);  
        String str = new String(byteArray);  
        return str;
    } 
    
    /* 
     * 可逆算法  BASE64 解密
     */  
    public static String Base64Dedode(String target){  
        byte[] result = Base64.decodeBase64(target);  
        return new String(result);  
    }
    
    public static byte[] Base64Dedode2(String target){  
        byte[] result = Base64.decodeBase64(target);  
        return result;  
    }
    

}
