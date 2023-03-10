package com.api.fichapaciente.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.fichapaciente.models.EspecialidadeModel;
import com.api.fichapaciente.repositories.EspecialidadeRepository;

@Service
public class EspecialidadeService {
  final EspecialidadeRepository especialidadeRepository;

  public EspecialidadeService(EspecialidadeRepository especialidadeRepository) {
    this.especialidadeRepository = especialidadeRepository;
  }

  public List<EspecialidadeModel> findAll() {
    return especialidadeRepository.findAll();
  }

  @Transactional
  public EspecialidadeModel salvar(EspecialidadeModel especialidadeModel) {
    return especialidadeRepository.save(especialidadeModel);
  }

  public Optional<EspecialidadeModel> findById(UUID id) {
    return especialidadeRepository.findById(id);
  }

  public void deleteEspecialidade(EspecialidadeModel especialidadeModel) {
    especialidadeRepository.delete(especialidadeModel);
  }

}
