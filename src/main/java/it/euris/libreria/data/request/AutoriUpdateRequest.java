package it.euris.libreria.data.request;

import it.euris.libreria.data.model.Autori;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoriUpdateRequest {

  private Long id;

  private String nome;

  private String cognome;

  public Autori toAutori() {
    return Autori.builder()
        .id(id)
        .nome(nome)
        .cognome(cognome)
        .build();
  }


}
