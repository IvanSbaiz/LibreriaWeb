package it.euris.libreria.service.impl;

import it.euris.libreria.service.LibriService;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import it.euris.libreria.data.model.Autori;
import it.euris.libreria.data.model.CasaEditrice;
import it.euris.libreria.data.model.Libri;
import it.euris.libreria.data.response.GenericResponse;
import it.euris.libreria.repository.AutoriRepository;
import it.euris.libreria.repository.CasaEditriceRepository;
import it.euris.libreria.repository.LibriRepository;

@Service
public class LibriImpl implements LibriService {

	private LibriRepository libriRepository;
	private AutoriRepository autoriRepository;
	private CasaEditriceRepository casaEditriceRepository;
	
	public LibriImpl(LibriRepository libriRepository, AutoriRepository autoriRepository, CasaEditriceRepository casaEditriceRepository) {
		this.libriRepository = libriRepository;
		this.autoriRepository = autoriRepository;
		this.casaEditriceRepository = casaEditriceRepository;
	}
	@Override
	public Libri getById(Long id) {
		Optional<Libri> libro = libriRepository.findById(id);
		if (libro.isPresent()) {
			return libro.get();
		}
		
		return null;
	}
	@Override
	public List<Libri> getAll() {
		return libriRepository.findAll();
	}
	
	public List<Libri> getByTitolo(String titolo) {
		return libriRepository.getByTitolo(titolo);
	}
	@Override
	public GenericResponse insert(Libri libro) {
		
		GenericResponse response = new GenericResponse();
		
		Optional<Autori> autore = autoriRepository.findById(libro.getAutore().getId());
		if (autore.isPresent()) {
			
			libro.setAutore(autore.get());
			
			Optional<CasaEditrice> casaEditrice = casaEditriceRepository.findById(libro.getCasaEditrice().getId());
			if (casaEditrice.isPresent()) {
				
				libro.setCasaEditrice(casaEditrice.get());
				
				Libri libroSaved = libriRepository.save(libro);
				response.setBody(libroSaved.toDto());
				response.setStatusCode(HttpStatus.CREATED);
				response.setMessage("Libro salvato correttamente");
				
			} else {
				response.setStatusCode(HttpStatus.NOT_FOUND);
				response.setMessage("Non è stata trovata la casa editrice");
			}
			
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND);
			response.setMessage("Non è stato trovato l'autore");
		}
		
		return response;
	}
	@Override
	public GenericResponse update(Libri libro) {
		
		GenericResponse response = new GenericResponse();
		
		if (libriRepository.findById(libro.getId()).isPresent()) {
			
			Optional<Autori> autore = autoriRepository.findById(libro.getAutore().getId());
			if (autore.isPresent()) {
				
				libro.setAutore(autore.get());
				response.setBody(libriRepository.save(libro).toDto());
				response.setStatusCode(HttpStatus.OK);
				response.setMessage("Libro aggiornato correttamente");
				
			} else {
				response.setStatusCode(HttpStatus.NOT_FOUND);
				response.setMessage("Libro non aggiornato. Non è stato trovato l'autore");
			}
			
		} else {
			response.setStatusCode(HttpStatus.NOT_FOUND);
			response.setMessage("Libro da aggiornare non trovato nel database");
		}
		
		return response;
	}
	@Override
	public GenericResponse deleteById(Long idLibro) {
		
		GenericResponse response = new GenericResponse();
			
		libriRepository.deleteById(idLibro);
		response.setStatusCode(HttpStatus.OK);
		response.setMessage("Libro correttamente cancellato");
		
		return response;
	}
	@Override
	public void delete(Libri libro) {
		libriRepository.delete(libro);
	}
	@Override
	public void deleteAll() {
		libriRepository.deleteAll();
	}
	@Override
	public void deleteByIdAutore(Long idAutore) {
		Optional<Autori> autore = autoriRepository.findById(idAutore);
		if (autore.isPresent()) {
			libriRepository.deleteByAutore(autore.get());
		}
	}

}
