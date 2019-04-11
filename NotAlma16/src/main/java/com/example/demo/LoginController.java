package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.service.MailService;
import com.example.demo.service.UserService;
import com.sun.mail.handlers.message_rfc822;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value="status" , required = false ) String status ,Model model) {

		if(status != null)
		{
			System.out.println(status);
			
			if(status.equals("ok"))
			{
				model.addAttribute("status" , "Uyeliginiz basarili ile tamamlandi");
			}
			else
			{
				model.addAttribute("status", "Hata tekrar deneyiniz!");
			}
			
		}
		return "login";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model ) {

		
		return "register";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		request.getSession().getAttribute(null);
		return "redirect:/login";
	}
	

//	@RequestMapping(value = "/reg/{key}", method = RequestMethod.GET)
//	public String regOk(@PathVariable("key") String key, Model model) {
//			if(userService.getfindByKey(key))
//					{
//						return "redirect:/login";
//					}
//			return "redirect:/login";
//	}
	
	
	
	@RequestMapping(value = "/reg/{key}", method = RequestMethod.GET)
	public String regOk(@PathVariable("key") String key, Model model) {
			if(userService.getfindByKey(key))
					{
						return "redirect:/login?status=ok";
					}
			return "redirect:/login?status=error";
	}
	
	@RequestMapping(value="/controlUser", method=RequestMethod.POST)
	public ResponseEntity<String> controlUser(@RequestBody User user, HttpServletRequest request)
	{
		User userm =	userService.getfindByUsernameAndPass(user);
		
		if(userm != null) 
		{
			request.getSession().setAttribute("user", userm); //Requestte istek attým istegin üzerinde session duruyor,
															  // bu session'ý addribute set ediyorum..
			return new ResponseEntity<>("OK",HttpStatus.OK);
		}
	    return new ResponseEntity<>("ERROR",HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user, HttpServletRequest request)
	{
		int status = control(user);
		if(status != 0)
		{
			return new ResponseEntity<>(status+"",HttpStatus.OK);
		}
		System.out.println(user.toString());
		
		if(userService.insert(user).equals(1l))
			{
				return new ResponseEntity<>("OK",HttpStatus.CREATED);
			}
		return new ResponseEntity<>("ERROR",HttpStatus.CREATED);
	}
	
	private int control(User user)
	{
		if(!user.getPass2().equals(user.getPass()))
		{
			return 1;
		}
		return 0;
		
	}
	
}
