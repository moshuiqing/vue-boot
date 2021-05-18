package com.home.lh.system.filter.log;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.home.lh.system.entity.SysLog;
import com.home.lh.system.entity.SysUser;
import com.home.lh.system.service.ISysLogService;
import com.home.lh.util.po.Global;
import com.home.lh.util.sysutil.DateUtils;
import com.home.lh.util.sysutil.SimpleUtils;
import com.home.lh.util.sysutil.SystemUtils;

import lombok.extern.slf4j.Slf4j;

@WebFilter
@Slf4j
public class LogFilter implements Filter {

	@Autowired
	private ISysLogService logService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("开始");

	}

	@SuppressWarnings("unused")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获取请求地址
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String ip = SystemUtils.getIpAddress(httpRequest); // 请求ip
		String url = httpRequest.getRequestURL().toString();// 请求地址
		if (url.indexOf(".") != -1) {
			chain.doFilter(httpRequest, httpResponse);
		} else {

			String a = (String) request.getAttribute("user");
		
			if (url.indexOf("found") > 0) {
				chain.doFilter(httpRequest, httpResponse);
				return;
			}
			String method = httpRequest.getMethod();
			// 请求时的系统时间
			LocalTime time1 = LocalTime.now();
			String isAbnormal = "正常";

			try {
				chain.doFilter(httpRequest, httpResponse);
				if (httpRequest.getAttribute("isAbnormal") != null) {
					isAbnormal = (String) httpRequest.getAttribute("isAbnormal");
				}
			} catch (Exception e) {

				e.printStackTrace();
				log.info("异常");
				isAbnormal = "异常";
			}

			// 响应时的系统时间
			LocalTime time2 = LocalTime.now();
			// 计算请求响应耗时
			Duration total = Duration.between(time1, time2);
			String timeConsuming = total.toMillis() + "";
			System.out.println(url + "耗时：" + timeConsuming + "----------" + ip);
			SysUser user = (SysUser) httpRequest.getSession().getAttribute(Global.SYSUSER);
			/*Subject subject = SecurityUtils.getSubject();
			Object key = subject.getPrincipal();
			if (key != null) {

				try {
					BeanUtils.copyProperties(user, key);
				} catch (Exception e) {

				}
			}*/
			String name = "";
			if (user != null) {
				name = user.getUserName();
			}
			SysLog syslog = new SysLog();
			syslog.setUrl(url);
			syslog.setIp(SimpleUtils.getIpAddress(httpRequest));
			syslog.setIsAbnormal(isAbnormal);
			syslog.setMethod(method);
			syslog.setOperator(name);
			syslog.setTimeConsuming(timeConsuming);
			syslog.setOperatingTime(DateUtils.backDate(new Date()));
			logService.save(syslog);

		}

	}

	@Override
	public void destroy() {
		log.info("结束");

	}

}
