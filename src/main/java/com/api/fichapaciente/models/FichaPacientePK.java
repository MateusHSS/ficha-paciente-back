package com.api.fichapaciente.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Embeddable;

@Embeddable
public class FichaPacientePK implements Serializable {

  private static final long serialVersionUID = 1L;

  private String numeroCarteiraPlano;

  private UUID idEspecialidade;

  private UUID idPlanoSaude;
  
  public String getNumeroCarteiraPlano() {
    return numeroCarteiraPlano;
  }

  public void setNumeroCarteiraPlano(String numeroCarteiraPlano) {
    this.numeroCarteiraPlano = numeroCarteiraPlano;
  }

  public UUID getIdEspecialidade() {
    return idEspecialidade;
  }

  public void setIdEspecialidade(UUID idEspecialidade) {
    this.idEspecialidade = idEspecialidade;
  }

  public UUID getIdPlanoSaude() {
    return idPlanoSaude;
  }

  public void setIdPlanoSaude(UUID idPlanoSaude) {
    this.idPlanoSaude = idPlanoSaude;
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj) return true;
    if(obj == null || getClass() != obj.getClass()) return false;
    FichaPacientePK fichaPacientePK = (FichaPacientePK) obj;
    
    return Objects.equals(idEspecialidade, fichaPacientePK.idEspecialidade) && Objects.equals(idPlanoSaude, fichaPacientePK.idPlanoSaude) && Objects.equals(numeroCarteiraPlano, fichaPacientePK.numeroCarteiraPlano);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idEspecialidade, idPlanoSaude, numeroCarteiraPlano);
  }
  
}
