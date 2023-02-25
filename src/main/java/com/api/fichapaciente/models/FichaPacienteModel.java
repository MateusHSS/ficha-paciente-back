package com.api.fichapaciente.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "FICHA_PACIENTE")
public class FichaPacienteModel {
  
  @EmbeddedId
  private FichaPacientePK id;

  @Column(nullable = false, length = 100)
  private String nomePaciente;

  public String getNomePaciente() {
    return nomePaciente;
  }
  public void setNomePaciente(String nomePaciente) {
    this.nomePaciente = nomePaciente;
  }
  public FichaPacientePK getId() {
    return id;
  }
  public void setId(FichaPacientePK id) {
    this.id = id;
  }
}
