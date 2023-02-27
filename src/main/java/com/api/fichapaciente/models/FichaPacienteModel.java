package com.api.fichapaciente.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "FICHA_PACIENTE")
public class FichaPacienteModel {
  
  @EmbeddedId
  FichaPacientePK id;

  public String getNumeroCarteiraPlano() {
    return id.getNumeroCarteiraPlano();
  }

  public UUID getIdEspecialidade() {
    return id.getEspecialidade().getId();
  }

  public UUID getIdPlanoSaude() {
    return id.getPlanoSaude().getId();
  }

  public void setId(FichaPacientePK id) {
    this.id = id;
  }

  @Column(nullable = false, length = 100)
  private String nomePaciente;

  public FichaPacienteModel(FichaPacientePK fichaPacientePK, String nomePaciente) {
    this.id = fichaPacientePK;
    this.nomePaciente = nomePaciente;
  }

  public FichaPacienteModel() {}

  public String getNomePaciente() {
    return nomePaciente;
  }

  public void setNomePaciente(String nomePaciente) {
    this.nomePaciente = nomePaciente;
  }
}
