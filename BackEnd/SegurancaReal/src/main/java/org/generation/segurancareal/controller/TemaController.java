package org.generation.segurancareal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.segurancareal.model.Tema;
import org.generation.segurancareal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	//Get mapping ALL
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	//Get mapping para encontrar a ID do tema
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Get mapping para encontrar a Descricao do tema
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
		
	}
	
	@GetMapping("/hashtag/{hashtag}")
	public ResponseEntity<List<Tema>> getByHashtag(@PathVariable String hashtag){
		return ResponseEntity.ok(repository.findAllByHashtagContainingIgnoreCase(hashtag));
	}
	
	@GetMapping("/assunto/{assunto}")
	public ResponseEntity<List<Tema>> getByAssunto(@PathVariable String assunto) {
		return ResponseEntity.ok(repository.findAllByAssuntoContainingIgnoreCase(assunto));
	}
	
	@PostMapping
	public ResponseEntity<Tema> post (@Valid @RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(tema));
		
	}
	
	@PutMapping
	public ResponseEntity<Tema> put (@Valid @RequestBody Tema tema ){
		return ResponseEntity.ok(repository.save(tema));
		
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	

}
