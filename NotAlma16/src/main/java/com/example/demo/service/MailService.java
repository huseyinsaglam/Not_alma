package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.HomeController;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;

	public void registerMail(String mail,String key) {

		SimpleMailMessage email = new SimpleMailMessage();
		
		email.setFrom("betonhs001@gmail.com");
		email.setTo(mail);
		email.setSubject("Uyeligi tamamla");
	  email.setText("Uyeligi tamamlamak icin asagýdaki linke tiklayiniz. \n \n"+HomeController.url+"/reg/" +key);
		//email.setText("Uyeligi tamamlamak icin asagýdaki linke tiklayiniz.");
	    mailSender.send(email);
	}

}
