package it.euris.libreria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.euris.libreria.data.dto.AutoriDto;
import it.euris.libreria.data.model.Autori;
import it.euris.libreria.service.AutoriService;
import it.euris.libreria.service.LibriService;

@RestController
@RequestMapping("/autori")
public class AutoriController {
	
	private AutoriService autoriService;
	private LibriService libriService;
	
	public AutoriController(AutoriService autoriService, LibriService libriService) {
		this.autoriService = autoriService;
		this.libriService = libriService;
	}
	
	// GET http://localhost:8080/autori
	@GetMapping
	public List<Autori> getAll() {
		return autoriService.getAll();
	}
	
	// GET http://localhost:8080/autori/1
	@GetMapping("/{idAutore}")
	public Autori getById(@PathVariable Long idAutore) {
		return autoriService.getById(idAutore);
	}
	
	@PostMapping
	public Autori insert(@RequestBody AutoriDto autore) {
		return autoriService.save(autore.toModel());
	}
	
	@PutMapping
	public Autori update(@RequestBody AutoriDto autore) {
		return autoriService.save(autore.toModel());
	}
	
	@DeleteMapping
	public void deleteAll() {
		libriService.deleteAll();
		autoriService.deleteAll();
	}
	
	@DeleteMapping("/{idAutore}")
	public void delete(@PathVariable Long idAutore) {
		libriService.deleteByIdAutore(idAutore);
		autoriService.deleteById(idAutore);
	}

}
