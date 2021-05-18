package com.home.lh.util.sysutil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author ai996
 * 系统工具类
 */
public class SystemUtils {
	
	
	public static void main(String[] args) {
		
		
		String pwd = MD5("MD5", "123456", "2019-3-10", 10);
		System.out.println(pwd);
	}

	/**
	 * @param type  算法类型
	 * @param pwd   加密的数据
	 * @param iterations  迭代次数
	 * @param salt 盐
	 * @return
	 */
	public static String MD5(String type,String pwd,String salt,Integer iterations) {
		Object obj = new SimpleHash(type, pwd, salt, iterations);
		return obj.toString();
	}
	
	/**
	 * @param strings
	 * @return
	 * 判断传入的值是否为空
	 */
	public static boolean isEmpty(String ... strings) {		
		for (String s : strings) {
			if(StringUtils.isEmpty(s)) {
				return true;
			}
		}		
		return false;
	}
	
	
    /**  
     * 概要说明 : 获取ip <br>
     * 详细说明 :      * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm 
     *  
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
     *  
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
     * 192.168.1.100 
     *  
     * 用户真实IP为： 192.168.1.110  <br>
*/
    public static String getIpAddress(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
	 *  
	 * 
	 * 
	 * 判断请求是否为ajax请求 
	 * 
	 */
	public static boolean isAjax(HttpServletRequest request) {
	    String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1)
        {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
        {
            return true;
        }

      
        return false;
	}
	
	/**
	 * @param s
	 * @return 获得图片
	 */
	public static List<String> getImg(String s) {
		String regex;
		List<String> list = new ArrayList<String>();
		regex = "src=\"(.*?)\"";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(s);
		while (ma.find()) {
			String src = ma.group();
			String url= src.substring(5, src.length()-1);
			list.add(url);
		}
		return list;
	}
}
