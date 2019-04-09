package com.example.demo.service;

import java.util.ArrayList;
import java.util.UUID;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDAO;
import com.example.demo.entity.Note;
import com.example.demo.entity.User;


@Service
@Transactional
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private MailService mailService;
	
	// getCurrentSession : mevcut session üzerinde iþlem yapar..iþlem kapsamýna baglýdýr.
	// iþlem bittiginde oturumu otomatik olarak siler ve kapatýr, böylece harici olarak yapmanýz gerekmez..
	public Long insert(User user)
	{
		String uuid =UUID.randomUUID().toString();
		user.setKeyreg(uuid);
		
		if( userDAO.insert(user)>0)
		{
			
			mailService.registerMail(user.getE_mail(), user.getKeyreg());
		}
			
		
		return 1l;
		
	}
	
	public void uptade(User user)
	{
		userDAO.uptade(user);
		
	}
	
	
	
	public User getfindByUsernameAndPass(User user)
	{
				
		return  userDAO.getfindByUsernameAndPass(user.getUsername(), user.getPass());
	}
	
	public User getfindByUsername(String username , String pass)
	{
		return  userDAO.getfindByUsername(username, pass);
	}
	
//	public User getfindByKey(String key)
//	{
//		return  userDAO.getfindByKey(key);
//	}
	
	
	public boolean getfindByKey(String key)
	{
		User user =userDAO.getfindByKey(key);
		if( user != null)
			{
			user.setActive(true);
			uptade(user);
			 	return true;
			}
		else
			{
				return false;
			}
	}

	
	
	
}
