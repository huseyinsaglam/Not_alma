package com.example.demo.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Note;
import com.example.demo.entity.User;


@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	// getCurrentSession : mevcut session üzerinde iþlem yapar..iþlem kapsamýna baglýdýr.
	// iþlem bittiginde oturumu otomatik olarak siler ve kapatýr, böylece harici olarak yapmanýz gerekmez..
	public Long insert(User user)
	{
		return (Long) sessionFactory.getCurrentSession().save(user);
		
	}
	
	public void uptade(User user)
	{
		sessionFactory.getCurrentSession().update(user);
		
	}
	
	
	
	public User getfindByUsernameAndPass(String username , String pass)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("From User WHERE username=:username AND pass=:pass AND active=:active")
				.setString("username", username)
				.setString("pass", pass)
				.setBoolean("active", true);
				
		User user = null;
		try {
			
			user =  (User) query.getSingleResult(); 
			
		} catch (javax.persistence.NoResultException e) {
			user = null;
		}
		return  user;
	}
	
	public User getfindByUsername(String username , String pass)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("From User WHERE username=:username")
				.setString("username", username);
		
		return  (User) query.getSingleResult();
	}
	
//	public User getfindByKey(String key)
//	{
//		Query query = sessionFactory.getCurrentSession().createQuery("From User WHERE keyreg=:key")
//				.setString("key", key);
//		
//		return  (User) query.getSingleResult();
//	}
	
	
	public User getfindByKey(String key)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("From User WHERE keyreg=:key")
				.setString("key", key);
		
		User user = null;
		try {
			
			user =  (User) query.getSingleResult(); 
			
		} catch (javax.persistence.NoResultException e) {
			user = null;
		}
		return  user;
	}
	
	
	
}
