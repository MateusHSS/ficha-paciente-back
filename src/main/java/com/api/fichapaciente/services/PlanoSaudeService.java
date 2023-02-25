package com.api.fichapaciente.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.fichapaciente.models.PlanoDeSaudeModel;
import com.api.fichapaciente.repositories.PlanoSaudeRespository;

@Service
public class PlanoSaudeService {
  final PlanoSaudeRespository planoSaudeRespository;

  public PlanoSaudeService(PlanoSaudeRespository planoSaudeRespository) {
    this.planoSaudeRespository = planoSaudeRespository;
  }

  public List<PlanoDeSaudeModel> findAll() {
    return planoSaudeRespository.findAll();
  }

  @Transactional
  public PlanoDeSaudeModel salvar(PlanoDeSaudeModel planoDeSaudeModel){
    return planoSaudeRespository.save(planoDeSaudeModel);
  }
}
