package com.academy.contatti.service;

import java.util.List;
import java.util.Optional;

import com.academy.contatti.model.Contatto;

public interface ContattiService {
	void saveContatto(Contatto contatto);
	List<Contatto> getAll();
	Optional<Contatto> findByID(String id);
	void deleteContatto(String id);
	
	List<Contatto> getByUsername(String username);
	List<Contatto> findByEtaBetween(int etaGT, int etaLT);
	List<Contatto> findByNomeStartingWith(String regexp);
	List<Contatto> findByNomeEndingWith(String regexp);
	long getCount();
}
