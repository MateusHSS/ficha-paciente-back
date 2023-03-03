package com.api.fichapaciente.repositories;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.fichapaciente.models.FichaPacienteModel;
import com.api.fichapaciente.models.FichaPacientePK;

@Repository
public interface FichaPacienteRepository extends JpaRepository<FichaPacienteModel, FichaPacientePK> {}
