package com.example.demo.endpoint;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dataTransferobject.UserLoginDTO;
import com.example.demo.entity.Note;
import com.example.demo.service.NoteService;


@RestController
@RequestMapping(value="/rest")
public class NoteEndpoint {
	

	@Autowired
	private NoteService service;
	
	// Kullanýcý adý ve password bilgilerine göre notlarý listeleyebiliriz.. 
	@RequestMapping(value="/getAll",method=RequestMethod.POST)
	public ResponseEntity<ArrayList<Note>> getNotes(@RequestBody UserLoginDTO login)
	{
		return new ResponseEntity<>(service.getAll(login),HttpStatus.OK);
	}

}
