package com.api.fichapaciente.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.fichapaciente.models.FichaPacienteModel;
import com.api.fichapaciente.repositories.FichaPacienteRepository;

@Service
public class FichaPacienteService {
  final FichaPacienteRepository fichaPacienteRepository;

  public FichaPacienteService(FichaPacienteRepository fichaPacienteRepository) {
    this.fichaPacienteRepository = fichaPacienteRepository;
  }

  public List<FichaPacienteModel> findAll() {
    return fichaPacienteRepository.findAll();
  }

  public FichaPacienteModel salvar(FichaPacienteModel fichaPacienteModel) {
    return fichaPacienteRepository.save(fichaPacienteModel);
  }
  
}
