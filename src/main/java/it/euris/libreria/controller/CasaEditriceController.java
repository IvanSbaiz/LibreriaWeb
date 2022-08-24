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

import it.euris.libreria.data.model.CasaEditrice;
import it.euris.libreria.data.response.GenericResponse;
import it.euris.libreria.service.CasaEditriceService;

@RestController
@RequestMapping("/casaeditrice")
public class CasaEditriceController {

  private CasaEditriceService casaEditriceService;

  public CasaEditriceController(CasaEditriceService casaEditriceService) {
    this.casaEditriceService = casaEditriceService;
  }

  @GetMapping
  public List<CasaEditrice> getAll() {
    return casaEditriceService.getAll();
  }

  @GetMapping("/{idCasaeditrice}")
  public CasaEditrice get(@PathVariable Long idCasaeditrice) {
    return casaEditriceService.get(idCasaeditrice);
  }

  @PostMapping
  public CasaEditrice save(@RequestBody CasaEditrice entity) {
    return casaEditriceService.save(entity);
  }

  @PutMapping
  public CasaEditrice update(@RequestBody CasaEditrice entity) {
    return casaEditriceService.save(entity);
  }

  @DeleteMapping("/{idCasaeditrice}")
  public Boolean deleteById(@PathVariable Long idCasaeditrice) {
    return casaEditriceService.deleteById(idCasaeditrice);
  }

}
