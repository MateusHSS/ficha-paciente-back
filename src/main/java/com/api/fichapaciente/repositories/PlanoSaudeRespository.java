package com.api.fichapaciente.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.fichapaciente.models.PlanoDeSaudeModel;

@Repository
public interface PlanoSaudeRespository extends JpaRepository<PlanoDeSaudeModel, UUID> {}
