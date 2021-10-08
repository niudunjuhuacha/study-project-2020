package com.iptv.season3.facetalk;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class APIValidateUtil {
	private static final String ENCODING = "UTF-8";
	private static final String httpMethod = "POST";
	private static final String ALGORITHM = "HmacSHA1";
	private static Mac mac_temp = null;
	static{
		try {
			mac_temp = Mac.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据传入参数生成签名字符串
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static String computeSignature(Map<String, String> parameters,String accessKeySecret) {
		// 将参数Key按字典顺序排序
		String[] sortedKeys = parameters.keySet().toArray(new String[] {});
		Arrays.sort(sortedKeys);

		final String SEPARATOR = "&";

		// 生成规范化请求字符串
		StringBuilder canonicalizedQueryString = new StringBuilder();
		for (String key : sortedKeys) {
			canonicalizedQueryString.append("&").append(percentEncode(key)).append("=")
					.append(percentEncode(parameters.get(key)));
		}

		// 生成用于计算签名的字符串 stringToSign
		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append(httpMethod).append(SEPARATOR);
		stringToSign.append(percentEncode("/")).append(SEPARATOR);
		stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));

        System.out.println("========accessKeySecret========"+accessKeySecret);
        System.out.println("========stringToSign.toString()========"+stringToSign.toString());
		// 注意accessKeySecret后面要加入一个字符"&"
		String signature = calculateSignature(accessKeySecret + "&", stringToSign.toString());
		return signature;
	}
	
	private static String calculateSignature(String key, String stringToSign) {
	    try {
    		// 使用HmacSHA1算法计算HMAC值
    		Mac mac =(Mac) mac_temp.clone();
    		//Mac mac = Mac.getInstance(ALGORITHM);
            System.out.println("======key.getBytes(ENCODING)=======:"+key.getBytes(ENCODING));
            System.out.println("======stringToSign.getBytes(ENCODING)=======:"+stringToSign.getBytes(ENCODING));
    		mac.init(new SecretKeySpec(key.getBytes(ENCODING), ALGORITHM));
    		byte[] signData = mac.doFinal(stringToSign.getBytes(ENCODING));
    		return new String(Base64.encodeBase64(signData));
	    } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
	}

	private static String percentEncode(String value) {
	    try {
    		// 使用URLEncoder.encode编码后，将"+","*","%7E"做替换
    		return value != null ? URLEncoder.encode(value, ENCODING).replace("+", "%20").replace("*", "%2A")
    				.replace("%7E", "~") : null;
	    } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException(e.getMessage(), e);
	    }
	}

	public static void main(String[] args){
		//ac39065b8566455b92c208471522cc88
		//28e459ee365b4253993fd450289af1ae
		Map<String,String> map = new HashMap<String,String>();
		map.put("AccessKeyId","c8c2d572f7594056ba7eca61b0313f39");
		map.put("token","1");
		//map.put("zyzid","1621735478637215929cb86994f57bd6d43b3689a288f");
		//map.put("username","330781199810261119");
		//map.put("password","gjh19981026");
//		APIValidateUtil tool = new APIValidateUtil();
		String key = computeSignature(map,"503387097bda47b5aa6b9305b5d4cceb");
		System.out.println(key);

	}

}
