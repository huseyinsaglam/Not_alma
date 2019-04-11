package com.example.demo;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Note;
import com.example.demo.security.LoginFilter;
import com.example.demo.service.MailService;
import com.example.demo.service.NoteService;

@Controller
public class HomeController {
	 
	 public static String url = "http://localhost:8080/NotAlma";
	
	@Autowired
    private NoteService noteService;

	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Model model , HttpServletRequest req) {

		return "redirect:/index";
		
	}
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homes(Model model , HttpServletRequest req) {

		return "redirect:/index";
		
	}
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model , HttpServletRequest req) {
		
		model.addAttribute("user", req.getSession().getAttribute("user")); //requestin üzerinde session aldým model attribute
																		   // user aldým
		System.out.println(req.getRemoteAddr());
		model.addAttribute("baslik", "Not alma Web Sitesi");
		
		model.addAttribute("notlar", noteService.getAll(1l));
		return "index";
		
	}
		
	@RequestMapping(value = "/detay/{id}", method = RequestMethod.GET)
	public String detay(@PathVariable("id") Long id ,Model model) {
	
		model.addAttribute("id",id); 
		//mailService.registerMail("kanaryam@yopmail.com","123");	                                            
		return "detail";
	}
	
	
	@RequestMapping(value = "/ekle", method = RequestMethod.GET)
	public String ekle(Model model) {

		return "addNote";
	}
	
	// Notu kayýt ettikten sonra ön tarafa kayýt etme mesajý 
	// Ön tarafa istek attým bu ön tarafta requestbody istegi alýyorum
	@RequestMapping(value="/addNote", method=RequestMethod.POST)
	public ResponseEntity<String> addNote(@RequestBody Note note, HttpServletRequest request)
	{
		System.out.println(note.toString());
		noteService.createNote(note,request);
		return new ResponseEntity<>("OK",HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/updateNote", method=RequestMethod.POST)
	public ResponseEntity<String> updateNote(@RequestBody Note note, HttpServletRequest request)
	{
		Note oldNote = noteService.getNotefindByid(note.getId());
		oldNote.setTitle(note.getTitle());
		oldNote.setContent(note.getContent());
		noteService.updateNote(oldNote,request);
		return new ResponseEntity<>("OK",HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/deleteNote", method=RequestMethod.POST)
	public ResponseEntity<String> deleteNote(@RequestBody Note note, HttpServletRequest request)
	{
		Note oldNote = noteService.getNotefindByid(note.getId());
		
		noteService.deleteNote(oldNote,request);
		return new ResponseEntity<>("OK",HttpStatus.OK);
	}
	

	@RequestMapping(value = "/error_404", method = RequestMethod.GET)
	public String error(Model model) {
		
		return "error_404";
	}
	
	@RequestMapping(value="/getNotes", method=RequestMethod.POST)
	public ResponseEntity<ArrayList<Note>> getNotes( HttpServletRequest request)
	{
		
		return new ResponseEntity<>(noteService.getAll(LoginFilter.user.getId()),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/getNote", method=RequestMethod.POST)
	public ResponseEntity<Note> getNotes(@RequestBody String id , HttpServletRequest request)
	{
		System.out.println(id);
		return new ResponseEntity<>(noteService.getNotefindByid(Long.parseLong(id)),HttpStatus.CREATED);
	}
	

}
