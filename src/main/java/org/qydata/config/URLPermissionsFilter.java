package org.qydata.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component("urlPermissionsFilter")
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		String curUrl = getRequestUrl(request);
		Subject subject = SecurityUtils.getSubject();
        return subject.isPermitted(curUrl);
	}
	/**
	 * 获取当前URL
	 * @param request	拦截请求request
	 * @return			返回flag
	 */
	private String getRequestUrl(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
		System.out.println(req.getRequestURI());
		String url = req.getRequestURI().substring(1).replaceAll("/",":");
		System.out.println(url);
//		String httpRequestUrl = req.getRequestURI()+"/";
//		System.out.println(httpRequestUrl);
//		Integer begin = httpRequestUrl.indexOf("/");
//		Integer end = httpRequestUrl.indexOf("/",httpRequestUrl.indexOf("/",begin+1)+1);
//		String url = httpRequestUrl.substring(begin,end).substring(1).replaceAll("/",":");
		return url;
	}

}
