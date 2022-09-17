package com.academy.contatti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.contatti.model.Contatto;
import com.academy.contatti.service.ContattiService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= "http://127.0.0.1:4200", allowedHeaders = "*")
public class ContattiRestController {
	
	@Autowired
	private ContattiService contattiService;
	
	@GetMapping("/contatti")
	public List<Contatto> getContatti() {
		return contattiService.getAll();
	}
	
	@GetMapping("/contatto/{username}")
	public List<Contatto> getContatto(@PathVariable String username) {
		return contattiService.getByUsername(username);
	}
	
	@GetMapping("/delete/{id}")
	public void deleteContatto(@PathVariable String id) {
		contattiService.deleteContatto(id);
	}
	
	@PostMapping("/save")
	public void saveContatto(@RequestBody Contatto contatto) {
		contattiService.saveContatto(contatto);
	}
	
	@PutMapping("/update")
	public void updateContatto(@RequestBody Contatto contatto) {
		contattiService.saveContatto(contatto);
	}
	
	@GetMapping("/contattiminorenni")
	public List<Contatto> getContattiMinorenni() {
		return contattiService.findByEtaBetween(-1, 17);
	}
	
}
