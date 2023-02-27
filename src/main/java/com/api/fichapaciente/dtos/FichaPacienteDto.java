package com.api.fichapaciente.dtos;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FichaPacienteDto {
  
  @NotBlank
  private String nomePaciente;
  @NotBlank
  @Size(max = 20)
  private String numeroCarteiraPlano;
  @NotBlank
  private String idEspecialidade;
  @NotBlank
  private String idPlanoSaude;
  
  public String getNomePaciente() {
    return nomePaciente;
  }
  public void setNomePaciente(String nomePaciente) {
    this.nomePaciente = nomePaciente;
  }
  public String getNumeroCarteiraPlano() {
    return numeroCarteiraPlano;
  }
  public void setNumeroCarteiraPlano(String numeroCarteiraPlano) {
    this.numeroCarteiraPlano = numeroCarteiraPlano;
  }
  public UUID getIdEspecialidade() {
    return UUID.fromString(idEspecialidade);
  }
  public void setIdEspecialidade(String idEspecialidade) {
    this.idEspecialidade = idEspecialidade;
  }
  public UUID getIdPlanoSaude() {
    return UUID.fromString(idPlanoSaude);
  }
  public void setIdPlanoSaude(String idPlanoSaude) {
    this.idPlanoSaude = idPlanoSaude;
  }

  
}
