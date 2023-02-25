package com.api.fichapaciente.dtos;

import javax.validation.constraints.NotBlank;

public class EspecialidadeDto {
  
  @NotBlank  
  private String nome;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
  
}
