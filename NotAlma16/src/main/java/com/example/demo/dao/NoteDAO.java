package com.example.demo.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Note;


@Repository
public class NoteDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	// getCurrentSession : mevcut session �zerinde i�lem yapar..i�lem kapsam�na bagl�d�r.
	// i�lem bittiginde oturumu otomatik olarak siler ve kapat�r, b�ylece harici olarak yapman�z gerekmez..
	public long insert(Note note)
	{
		return (long) sessionFactory.getCurrentSession().save(note);
		
	}
	
	public void uptade(Note note)
	{
		sessionFactory.getCurrentSession().update(note);
		
	}
	
	
	// burada g�nderdigimiz notun i�erisinde id varsa buna bak�yor g�ncelleme yap�yor yoksa id ekleme yap�yor..
//	public void persist (Note note)
//	{
//		sessionFactory.getCurrentSession().persist(note);
//	}
	
	
	public void delete(Note note)
	{
		sessionFactory.getCurrentSession().delete(note);
	}
	
	// READ KiSMi
	// B�t�n notlar� d�nd�rme
	public ArrayList<Note> getAll()
	{
		Query query = sessionFactory.getCurrentSession().createQuery("From Note");
		return (ArrayList<Note>) query.getResultList();
	}
	
	// kisiye ait notlar� d�nd�rme
	public ArrayList<Note> getAll(Long user_id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("From Note WHERE user_id=:user_id order by id desc")
				.setLong("user_id", user_id);
		return (ArrayList<Note>) query.getResultList();
	}
	
	
	public Note getfindByid(Long id)
	{
		Query query = sessionFactory.getCurrentSession().createQuery("From Note WHERE id=:id")
				.setLong("id",id);
				
		return  (Note) query.getSingleResult();
	}
	
	
	
}
