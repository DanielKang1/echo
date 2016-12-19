package com.echo.utils;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
 

public class DESUtils {
	
	//指定DES加解密所用的秘钥
	private static Key key;
	private static String KEY_STR = "myKey";
	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(KEY_STR.getBytes()));
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对str进行DES加密，返回Base64编码的加密字符串
	 */
	public static String getEncryptString(String str) {
		try {
			byte[] strBytes = str.getBytes("UTF8");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return EncodeUtils.Base64Endode(encryptStrBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对Base64编码的加密字符串进行解密，返回解密后的字符串
	 */
	public static String getDecryptString(String str) {
		try {
			byte[] strBytes = EncodeUtils.Base64Dedode2(str);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptStrBytes = cipher.doFinal(strBytes);
			return new String(decryptStrBytes, "UTF8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) throws Exception {
//		if (args == null || args.length < 1) {
//			System.out.println("请输入要加密的字符，用空格分隔.");
//		} else {
//			for (String arg : args) {
//				System.out.println(arg + ":" + getEncryptString(arg));
//			}
//		}
		
//		System.out.println(getDecryptString("bkkGrKE9iEMfJAfVsP+M2w=="));
//		System.out.println(getDecryptString("gJQ9O+q34qk="));
		System.out.println(getEncryptString("张一"));
//		String salt = EncodeUtils.getSalt(16);
//		System.out.println(salt);
//		String pwd = "admin1";
//		String hash = EncodeUtils.SHA1Encode(salt+pwd);
//		System.out.println(hash);
	}
}
