package com.api.fichapaciente.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLANO_SAUDE")
public class PlanoDeSaudeModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  
  @Column(nullable = false, length = 50)
  private String nome;

  public PlanoDeSaudeModel(String nome) {
    this.nome = nome;
  }

  public PlanoDeSaudeModel() {}

  public UUID getId() {
    return id;
  }
  public void setId(UUID id) {
    this.id = id;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
}
