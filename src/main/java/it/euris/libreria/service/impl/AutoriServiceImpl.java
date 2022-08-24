package it.euris.libreria.service.impl;

import it.euris.libreria.data.response.GenericResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import it.euris.libreria.data.model.Autori;
import it.euris.libreria.repository.AutoriRepository;
import it.euris.libreria.service.AutoriService;

@Service
public class AutoriServiceImpl implements AutoriService {
	
	private AutoriRepository autoriRepository;
	
	public AutoriServiceImpl(AutoriRepository autoriRepository) {
		this.autoriRepository = autoriRepository;
	}

	@Override
	public Autori getById(Long id) {
		Optional<Autori> autore = autoriRepository.findById(id);
		if (autore.isPresent()) {
			return autore.get();
		}
		
		return null;
	}
	
	@Override
	public List<Autori> getAll() {
		return autoriRepository.findAll();
	}
	
	@Override
	public List<Autori> getAll(String orderColumnName, Direction dir) {
		
		Sort sort = Sort.by(orderColumnName).descending();
		if (Direction.ASC.equals(dir)) {
			sort = Sort.by(orderColumnName).ascending();
		}
		
		return autoriRepository.findAll(sort);
	}
	
	@Override
	public List<Autori> getAllPageable(int nrPage, int pageSize, String orderColumnName, Direction dir) {
		
		Sort sort = null;
		if (Direction.DESC.equals(dir)) {
			sort = Sort.by(orderColumnName).descending();
		} else {
			sort = Sort.by(orderColumnName).ascending();
		}
		
		Page<Autori> autoriPage = autoriRepository.findAll(PageRequest.of(nrPage, pageSize, sort));
		return autoriPage.getContent();
	}
	
	@Override
	public Autori save(Autori autore) {
		Autori autoreToSave = Autori.builder().nome(autore.getNome()).cognome(autore.getCognome()).build();
		return autoriRepository.save(autoreToSave);
	}

	@Override
	public GenericResponse update(Autori autore) {
		GenericResponse response = new GenericResponse();

		if (autoriRepository.findById(autore.getId()).isPresent()) {

				response.setStatusCode(HttpStatus.OK);
				response.setMessage("Autore aggiornato correttamente");

			} else {
				response.setStatusCode(HttpStatus.NOT_FOUND);
				response.setMessage("Autore non aggiornato. Non è stato trovato l'autore");
			}

		return response;
	}

	@Override
	public void delete(Autori autore) {
		autoriRepository.delete(autore);
	}
	
	@Override
	public void deleteById(Long idAutore) {
		autoriRepository.deleteById(idAutore);
	}
	
	@Override
	public void deleteAll() {
		autoriRepository.deleteAll();
	}

}
