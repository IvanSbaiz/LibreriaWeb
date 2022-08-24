package it.euris.libreria.service;

import it.euris.libreria.data.model.Libri;
import it.euris.libreria.data.response.GenericResponse;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import it.euris.libreria.data.model.Autori;

public interface AutoriService {

	public Autori getById(Long id);
	
	public List<Autori> getAll();
	
	public List<Autori> getAll(String orderColumnName, Direction dir);
	
	public List<Autori> getAllPageable(int nrPage, int pageSize, String orderColumnName, Direction dir) ;
	
	public Autori save(Autori autore);

	public GenericResponse update(Autori autore);
	
	public void delete(Autori autore);
	
	public void deleteById(Long idAutore);
	
	public void deleteAll();
}
