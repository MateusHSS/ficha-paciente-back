package com.api.fichapaciente.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "FICHA_PACIENTE")
public class FichaPacienteModel {
  
  @EmbeddedId
  FichaPacientePK pk;

  public String getNumeroCarteiraPlano() {
    return pk.getNumeroCarteiraPlano();
  }

  public EspecialidadeModel getEspecialidade() {
    return pk.getEspecialidade();
  }

  public PlanoDeSaudeModel getPlanoSaude() {
    return pk.getPlanoSaude();
  }

  public void setPk(FichaPacientePK pk) {
    this.pk = pk;
  }

  @Column(nullable = false, length = 100)
  private String nomePaciente;

  public FichaPacienteModel(FichaPacientePK fichaPacientePK, String nomePaciente) {
    this.pk = fichaPacientePK;
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
