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


@Component // spring in kýsmýna al artýk sen kullan demektir. component scan ile aynýdýr..
@Scope("session") // scope olsun her sessionda bir tane yaratýlsýn istiyoruz..
public class LoginFilter implements Filter {

	
	public static User user = null;

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if(req.getRequestURI().contains("login")) // icerisinde login geçiyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yapýp girebilsin
			 return;
		}
		
		
		if(req.getRequestURI().contains("register")) // icerisinde login geçiyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yapýp girebilsin
			 return;
		}
		
		
		if(req.getRequestURI().contains("controlUser")) // icerisinde login geçiyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yapýp girebilsin
			 return;
		}
		
		if(req.getRequestURI().contains("addUser")) // icerisinde login geçiyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yapýp girebilsin
			 return;
		}
		
		if(req.getRequestURI().contains("logout")) // icerisinde login geçiyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yapýp girebilsin
			 return;
		}
		
		if(req.getRequestURI().contains("reg")) // icerisinde login geçiyorsa 
		{
			 chain.doFilter(request, response); // istedigi yerde filtlemeyi yapýp girebilsin
			 return;
		}
		
		
		User user = (User) req.getSession().getAttribute("user"); // session üzerinde attribute kayýtlý user bilgilerini aldým.
		this.user = user;
		if (user != null) // user bos degilse istedigi yerde filtreleme yapsýn...
		{
			chain.doFilter(request, response); // istedigi yerde filtlemeyi yapýp girebilsin
			return;
		} 
		else { // eger bos ise o zaman bu adamý logine gönder
			res.sendRedirect("login");
		} 


		// chain.doFilter(request, response); // istedigi yerde filtlemeyi yapýp girebilsin
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
