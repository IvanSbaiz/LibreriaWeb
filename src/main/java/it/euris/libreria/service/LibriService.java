package it.euris.libreria.service;

import it.euris.libreria.data.model.CasaEditrice;
import it.euris.libreria.data.model.Libri;
import it.euris.libreria.data.response.GenericResponse;
import java.util.List;

public interface LibriService {

  public Libri getById(Long id);

  public List<Libri> getAll();

  public GenericResponse insert(Libri libro);

  public GenericResponse update(Libri libro);

  public GenericResponse deleteById(Long idLibro);

  public void delete(Libri libro);

  public void deleteAll();

  public void deleteByIdAutore(Long idAutore);



}
