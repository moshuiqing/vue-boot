package com.home.lh.util.spth;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SpthQmUtil {

	static String appId = "5fd093b419d2a9b6f835db46";
	static String appKey="5fd093b419d2a9b6f835db46.cloudrtc.myhuaweicloud.com";
	
	public static Map<String, Object> getQm() {

		Map<String, Object> map = new HashMap<String, Object>();
		long ctime = System.currentTimeMillis() / 1000 + 60 * 60; // 有效时间为1小时，单位是秒
		String content = appId + "+" + "1" + "+" + "admin" + "+" + ctime;
		String signatureStr = hmacSha(appKey, content, "HmacSHA256");
		map.put("appId", appId);
		map.put("appKey", appKey);
		map.put("ctime", ctime);
		map.put("role", 0);
		map.put("signature", signatureStr);
		
		return map;

	}
	
	 static String hmacSha(String KEY, String VALUE, String SHA_TYPE) {
	        try {
	            SecretKeySpec signingKey = new SecretKeySpec(KEY.getBytes("UTF-8"), SHA_TYPE);
	            Mac mac = Mac.getInstance(SHA_TYPE);
	            mac.init(signingKey);
	            byte[] rawHmac = mac.doFinal(VALUE.getBytes("UTF-8"));

	            byte[] hexArray = {
	                    (byte) '0', (byte) '1', (byte) '2', (byte) '3',
	                    (byte) '4', (byte) '5', (byte) '6', (byte) '7',
	                    (byte) '8', (byte) '9', (byte) 'a', (byte) 'b',
	                    (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f'
	            };
	            byte[] hexChars = new byte[rawHmac.length * 2];
	            for (int j = 0; j < rawHmac.length; j++) {
	                int v = rawHmac[j] & 0xFF;
	                hexChars[j * 2] = hexArray[v >>> 4];
	                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	            }
	            return new String(hexChars);
	        } catch (Exception ex) {
	            throw new RuntimeException(ex);
	        }
	    }

	 public static void main(String[] args) {
		
		 System.out.println(SpthQmUtil.getQm());
		 
	}

}
