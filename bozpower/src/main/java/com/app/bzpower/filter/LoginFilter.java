package com.app.bzpower.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.bzpower.entity.Admin;

public class LoginFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse response = (HttpServletResponse) arg1;
		//获取工程的绝对路径
//		String url = req.getSession().getServletContext().getRealPath("/");
		//System.out.println(url);
		//获取请求页面的相对路径
		String currentURL = req.getRequestURI();
		//截取到当前文件名用于比较
        //String targetURL = currentURL.substring(currentURL.indexOf("/",2),currentURL.length());
		String pageName = currentURL.substring(currentURL.lastIndexOf("/") + 1);
		System.err.println("拦截了：" + pageName + "请求");
		if(!"login.jsp".equals(pageName)  && !"forgetpass.jsp".equals(pageName)) {
			HttpSession session = req.getSession();
			Admin admin = (Admin) session.getAttribute("admin");
			if(session == null || admin == null) {
				if("login.jsp".equals(pageName))
					response.sendRedirect(req.getContextPath()+"/login.jsp");
				else if("forgetpass.jsp".equals(pageName))
					response.sendRedirect(req.getContextPath()+"/forgetpass.jsp");
				else 
					response.sendRedirect(req.getContextPath()+"/login.jsp");
				return ;
			}else 
				arg2.doFilter(req, response);
		}else {
			arg2.doFilter(req, response);
		}
		
//		System.out.println("u = " + u);
//		System.out.println("path = " + currentURL);
//		System.out.println(" targetURL = " + targetURL);
		
		
	}

	public void init(FilterConfig request) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
