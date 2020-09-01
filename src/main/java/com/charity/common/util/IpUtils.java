package com.charity.common.util;



import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * ip工具类
 */
public class IpUtils {

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		if (StringUtils.isNotEmpty(ip) && ip.indexOf(",") > -1) {
            String[] arr = ip.split(",");
            if (arr != null && arr.length > 0) {
                for (String s : arr) {
                    if (StringUtils.isNotEmpty(s) && !StringUtils.equalsIgnoreCase(s,"unknown")) {
                        ip = s;
                        break;
                    }
                }
            }
        }
		
		return ip;
	}
	public static Long ip2Long(String ip){
		long result = 0;
		String[] ipAddressInArray = ip.split("\\.");
		try {
			for (int i = 3; i >= 0; i--) {
				result |= Long.parseLong(ipAddressInArray[3 - i]) << (i * 8);
			}
		}catch (NumberFormatException e){

		}
		return result;
	}
	public static String long2Ip(long ip) {
		StringBuilder result = new StringBuilder(16);
		for (int i = 0; i < 4; i++) {
			result.insert(0,'.');
			result.insert(0,Long.toString(ip & 0xff));
			ip = ip >> 8;
		}
		result.deleteCharAt(result.length()-1);
		return result.toString();
	}
//	public static String ip2Address(String ip) {
//		try {
//			String s = Net_tools.get("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
//			String s1 = String_tools.unescape(s);
//			Map<String, String> data = new JSONString(s1).getMap("data");
//			return data.get("country")+" "+data.get("area")+" "+data.get("region")+" "+data.get("city")+" "+data.get("isp");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
}
