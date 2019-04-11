 package com.example.demo.service;

import java.util.ArrayList;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.NoteDAO;
import com.example.demo.entity.Note;
import com.example.demo.security.LoginFilter;

@Service
@Transactional
public class NoteService {
	
	@Autowired
	private NoteDAO noteDAO;

	
//	public Long createNote(Note note)
//	{
//		return noteDAO.insert(note);
//	}

	
	public Long createNote(Note note, HttpServletRequest request)
	{
		// user id sonra degistir..
		note.setUser_id(LoginFilter.user.getId());
		return noteDAO.insert(note);
	}
	
	

	public Long updateNote(Note note, HttpServletRequest request)
	{
		 noteDAO.uptade(note);
		 return 1l;
	}
	
	
	public Long deleteNote(Note note, HttpServletRequest request)
	{
		 noteDAO.delete(note);
		 return 1l;
	}
	
	
	public Note getNotefindByid(Long id)
	{
		
		return  noteDAO.getfindByid(id);
	}
	
	public ArrayList<Note> getAll()
	{
		return noteDAO.getAll();
	}
	
	public ArrayList<Note> getAll(Long userId)
	{
		return noteDAO.getAll(userId);
	}

	

	
}
