package com.echo.utils;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

/**
 * 用于将dbinfo中的用户名与密码解密
 * PropertyPlaceholderConfigurer可对所有属性值进行转换
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {	
	private String[] encryptPropNames ={"jdbc.user","jdbc.password"};
	
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {		
		if(isEncryptProp(propertyName)){
			String decryptValue = DESUtils.getDecryptString(propertyValue);
			return decryptValue;
		}else{
			return propertyValue;
		}
	}
	
	/**
	 * 判断是否是加密的属性
	 */
	private boolean isEncryptProp(String propertyName){
		for(String encryptPropName:encryptPropNames){
			if(encryptPropName.equals(propertyName)){
				return true;
			}
		}
		return false;
	}
}
