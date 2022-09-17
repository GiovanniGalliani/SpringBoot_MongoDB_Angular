package com.academy.contatti.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.academy.contatti.model.Contatto;

@Repository("contattiRepository")
public interface ContattiRepository extends MongoRepository<Contatto, String> {
	// Find by X
	List<Contatto> findByUsername(String username);
	List<Contatto> findByEtaBetween(int etaGT, int etaLT);
	List<Contatto> findByNomeStartingWith(String regexp);
	List<Contatto> findByNomeEndingWith(String regexp);
	long count();
}
