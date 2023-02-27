package com.api.fichapaciente.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class FichaPacientePK implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false, length = 20)
  private String numeroCarteiraPlano;

  @ManyToOne
  @JoinColumn(name = "id_especialidade")
  private EspecialidadeModel especialidade;

  @ManyToOne
  @JoinColumn(name = "id_plano_saude")
  private PlanoDeSaudeModel planoSaude;

  public FichaPacientePK(String numeroCarteiraPlano, EspecialidadeModel especialidade, PlanoDeSaudeModel planoSaude) {
    this.numeroCarteiraPlano = numeroCarteiraPlano;
    this.especialidade = especialidade;
    this.planoSaude = planoSaude;
  }

  public FichaPacientePK() {}
  
  public String getNumeroCarteiraPlano() {
    return numeroCarteiraPlano;
  }

  public void setNumeroCarteiraPlano(String numeroCarteiraPlano) {
    this.numeroCarteiraPlano = numeroCarteiraPlano;
  }

  public EspecialidadeModel getEspecialidade() {
    return especialidade;
  }

  public void setEspecialidade(EspecialidadeModel especialidade) {
    this.especialidade = especialidade;
  }

  public PlanoDeSaudeModel getPlanoSaude() {
    return planoSaude;
  }

  public void setPlanoSaude(PlanoDeSaudeModel planoSaude) {
    this.planoSaude = planoSaude;
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj) return true;
    if(obj == null || getClass() != obj.getClass()) return false;
    FichaPacientePK fichaPacientePK = (FichaPacientePK) obj;
    
    return Objects.equals(especialidade, fichaPacientePK.especialidade) && Objects.equals(planoSaude, fichaPacientePK.planoSaude) && Objects.equals(numeroCarteiraPlano, fichaPacientePK.numeroCarteiraPlano);
  }

  @Override
  public int hashCode() {
    return Objects.hash(especialidade, planoSaude, numeroCarteiraPlano);
  }
  
}
