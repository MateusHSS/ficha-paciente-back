package com.api.fichapaciente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.api.fichapaciente.models.FichaPacienteModel;
import com.api.fichapaciente.models.FichaPacientePK;
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

  public List<FichaPacienteModel> findAll(Example<FichaPacienteModel> fichaPacienteExample) {
    return fichaPacienteRepository.findAll(fichaPacienteExample);
  }

  public FichaPacienteModel salvar(FichaPacienteModel fichaPacienteModel) {
    return fichaPacienteRepository.save(fichaPacienteModel);
  }
  
  public Optional<FichaPacienteModel> findById(FichaPacientePK id) {
    return fichaPacienteRepository.findById(id);
  }

  public void deleteFichaPaciente(FichaPacienteModel fichaPaciente) {
    fichaPacienteRepository.delete(fichaPaciente);
  }
}
