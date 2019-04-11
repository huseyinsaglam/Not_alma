package com.example.demo.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.sun.xml.internal.ws.client.SenderException;


@Component // spring in k�sm�na al art�k sen kullan demektir. component scan ile ayn�d�r..
@Scope("session") // scope olsun her sessionda bir tane yarat�ls�n istiyoruz..
public class LoginFilter implements Filter {

	
	public static User user = null;

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if(req.getRequestURI().contains("login")) // icerisinde login ge�iyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yap�p girebilsin
			 return;
		}
		
		
		if(req.getRequestURI().contains("register")) // icerisinde login ge�iyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yap�p girebilsin
			 return;
		}
		
		
		if(req.getRequestURI().contains("controlUser")) // icerisinde login ge�iyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yap�p girebilsin
			 return;
		}
		
		if(req.getRequestURI().contains("addUser")) // icerisinde login ge�iyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yap�p girebilsin
			 return;
		}
		
		if(req.getRequestURI().contains("logout")) // icerisinde login ge�iyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yap�p girebilsin
			 return;
		}
		
		if(req.getRequestURI().contains("reg")) // icerisinde login ge�iyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yap�p girebilsin
			 return;
		}
		
		
		User user = (User) req.getSession().getAttribute("user"); // session �zerinde attribute kay�tl� user bilgilerini ald�m.
		this.user = user;
		if (user != null) // user bos degilse istedigi yerde filtreleme yaps�n...
		{
			chain.doFilter(request, response); // istedigi yerde filtlemeyi yap�p girebilsin
			return;
		} 
		else { // eger bos ise o zaman bu adam� logine g�nder
			res.sendRedirect("login");
		} 


		// chain.doFilter(request, response); // istedigi yerde filtlemeyi yap�p girebilsin
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
