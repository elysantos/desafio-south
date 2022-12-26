package com.elysantos.desafiosouth.model.domain;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Associado {
  private UUID   id;
  private String cpf;
  private AssociadoEnabled associadoEnabled;
}
