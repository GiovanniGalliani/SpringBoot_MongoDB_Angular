package com.academy.contatti.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.academy.contatti.model.Contatto;
import com.academy.contatti.service.ContattiService;

@Controller
public class ContattiController {
	
	@Autowired
	private ContattiService contattiService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		List<Contatto> listaContatti = contattiService.getAll();
		mv.addObject("listaContatti", listaContatti);
		mv.addObject("contatto", new Contatto());
		return mv;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView saveContatto(Contatto contatto) {
		contattiService.saveContatto(contatto);
		return new ModelAndView("redirect:/");
	}
	
	 @RequestMapping(value="/modifica/{id}", method=RequestMethod.GET)
	 public ModelAndView modificaContatto(@PathVariable String id) {
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("editpage");
		 mv.addObject("contatto", contattiService.findByID(id));
		 return mv;
	 }
	 
	 @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	 public ModelAndView deleteContatto(@PathVariable String id) {
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("deletepage");
		 Optional<Contatto> contatto=contattiService.findByID(id);
		 mv.addObject("contatto", contatto.get());
		 return mv;
	 }
	 
	 @RequestMapping(value="/delete", method=RequestMethod.POST)
	 public ModelAndView applicaDelete(Contatto contatto) {
		 contattiService.deleteContatto(contatto.getId());
		 return new ModelAndView("redirect:/");
	 }
	 
	 @RequestMapping(value="/statistica", method=RequestMethod.GET)
	 public ModelAndView getStatistiche(@RequestParam(name="username") String username) {
		 List<Contatto> listaContatti = contattiService.getByUsername(username);
		 List<Contatto> contattiGiovani = contattiService.findByEtaBetween(-1, 21);
		 List<Contatto> contattiInizianoConO = contattiService.findByNomeStartingWith("O");
		 List<Contatto> contattiTerminanoConR= contattiService.findByNomeEndingWith("r");
		 Long conteggio = contattiService.getCount();
		 
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("stat");
		 mv.addObject("listaContatti", listaContatti);
		 mv.addObject("contattiGiovani", contattiGiovani);
		 mv.addObject("contattiInizianoConO", contattiInizianoConO);
		 mv.addObject("contattiTerminanoconR", contattiTerminanoConR);
		 mv.addObject("conteggio", conteggio);
	
		 return mv;
	 }
	 
}