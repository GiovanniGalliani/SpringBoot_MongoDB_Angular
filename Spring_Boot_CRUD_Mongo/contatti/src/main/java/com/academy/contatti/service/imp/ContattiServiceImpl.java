package com.academy.contatti.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.contatti.model.Contatto;
import com.academy.contatti.repository.ContattiRepository;
import com.academy.contatti.service.ContattiService;

@Service("contattoService")
public class ContattiServiceImpl implements ContattiService {
	
	@Autowired
	private ContattiRepository contattiRepository;

	@Override
	public void saveContatto(Contatto contatto) {
		contattiRepository.save(contatto);		
	}

	@Override
	public List<Contatto> getAll() {
		return contattiRepository.findAll();
	}

	@Override
	public Optional<Contatto> findByID(String id) {
		return contattiRepository.findById(id);
	}

	@Override
	public void deleteContatto(String id) {
		contattiRepository.deleteById(id);
	}

	@Override
	public List<Contatto> getByUsername(String username) {
		return contattiRepository.findByUsername(username);
	}

	@Override
	public List<Contatto> findByEtaBetween(int etaGT, int etaLT) {
		return contattiRepository.findByEtaBetween(etaGT,etaLT);
	}

	@Override
	public List<Contatto> findByNomeStartingWith(String regexp) {
		return contattiRepository.findByNomeStartingWith(regexp);
	}

	@Override
	public List<Contatto> findByNomeEndingWith(String regexp) {
		return contattiRepository.findByNomeEndingWith(regexp);
	}

	@Override
	public long getCount() {
		return contattiRepository.count();
	}
}