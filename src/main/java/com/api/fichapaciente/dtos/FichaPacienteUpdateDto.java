package com.api.fichapaciente.dtos;

import javax.validation.constraints.NotBlank;

public class FichaPacienteUpdateDto {
  
  @NotBlank
  private String nomePaciente;

  public String getNomePaciente() {
    return nomePaciente;
  }

  public void setNomePaciente(String nomePaciente) {
    this.nomePaciente = nomePaciente;
  }

}
