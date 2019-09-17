package br.com.drugstore.filter;

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

import br.com.drugstore.model.Funcionario;

public class MyFIlter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest) request;
		HttpServletResponse res= (HttpServletResponse) response;
		System.out.println("==Filter===");
		
		HttpSession session= req.getSession();
		Funcionario f= (Funcionario) session.getAttribute("user");
		
		String url= req.getRequestURL().toString();
		System.out.println(url);
		
		if (!url.contains("/pages/login.xhtml") && f == null) {
			res.sendRedirect(req.getServletContext().getContextPath()+ "/pages/login.xhtml");
			System.out.println("If");
		} else {
			System.out.println("Else");
			chain.doFilter(req, response);
		}
	}
	
	@Override
	public void destroy() {
	}


}
