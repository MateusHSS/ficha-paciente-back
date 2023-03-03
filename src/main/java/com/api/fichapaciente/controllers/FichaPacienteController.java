package com.api.fichapaciente.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.fichapaciente.dtos.FichaPacienteDto;
import com.api.fichapaciente.dtos.FichaPacienteUpdateDto;
import com.api.fichapaciente.models.EspecialidadeModel;
import com.api.fichapaciente.models.FichaPacienteModel;
import com.api.fichapaciente.models.FichaPacientePK;
import com.api.fichapaciente.models.PlanoDeSaudeModel;
import com.api.fichapaciente.services.EspecialidadeService;
import com.api.fichapaciente.services.FichaPacienteService;
import com.api.fichapaciente.services.PlanoSaudeService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/ficha-paciente")
public class FichaPacienteController {
  final FichaPacienteService fichaPacienteService;
  final EspecialidadeService especialidadeService;
  final PlanoSaudeService planoSaudeService;

  public FichaPacienteController(FichaPacienteService fichaPacienteService, EspecialidadeService especialidadeService, PlanoSaudeService planoSaudeService) {
    this.fichaPacienteService = fichaPacienteService;
    this.especialidadeService = especialidadeService;
    this.planoSaudeService = planoSaudeService;
  }

  @GetMapping
  public ResponseEntity<List<FichaPacienteModel>> listar() {
    return ResponseEntity.status(HttpStatus.OK).body(fichaPacienteService.findAll());
  }

  @PostMapping
  public ResponseEntity<Object> salvarFichaPaciente(@RequestBody @Valid FichaPacienteDto fichaPacienteDto){
    Optional<EspecialidadeModel> especialidadeModelOptional = especialidadeService.findById(fichaPacienteDto.getIdEspecialidade());

    if(!especialidadeModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrada");
    }

    Optional<PlanoDeSaudeModel> planoSaudeModelOptional = planoSaudeService.findById(fichaPacienteDto.getIdPlanoSaude());

    if(!planoSaudeModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano de saúde não encontrado");
    }

    var fichaPacientePK = new FichaPacientePK(fichaPacienteDto.getNumeroCarteiraPlano(), especialidadeModelOptional.get(), planoSaudeModelOptional.get());
    Optional<FichaPacienteModel> fichaPacienteModelOptional = fichaPacienteService.findById(fichaPacientePK);

    if(fichaPacienteModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma ficha para esse número de carteira para essa especialidade e plano de saúde");
    }

    var fichaPacienteModel = new FichaPacienteModel(fichaPacientePK, fichaPacienteDto.getNomePaciente());

    return ResponseEntity.status(HttpStatus.CREATED).body(fichaPacienteService.salvar(fichaPacienteModel));
  }

  @GetMapping("/{numeroCarteiraPlano}/{idEspecialidade}/{idPlanoSaude}")
  public ResponseEntity<Object> getByNumeroCarteiraEspecialidadePlano(@PathVariable(name = "numeroCarteiraPlano") String numeroCarteiraPlano, @PathVariable(name = "idEspecialidade") String idEspecialidade, @PathVariable(name = "idPlanoSaude") String idPlanoSaude ) {
    Optional<EspecialidadeModel> especialidadeModelOptional = especialidadeService.findById(UUID.fromString(idEspecialidade));

    if(!especialidadeModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrada");
    }

    Optional<PlanoDeSaudeModel> planoSaudeModelOptional = planoSaudeService.findById(UUID.fromString(idPlanoSaude));

    if(!planoSaudeModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano de saúde não encontrado");
    }
    
    FichaPacienteModel fichaPaciente = new FichaPacienteModel();
    fichaPaciente.setPk(new FichaPacientePK(numeroCarteiraPlano, especialidadeModelOptional.get(), planoSaudeModelOptional.get()));
    
    List<FichaPacienteModel> fichaPacienteModelOptional = fichaPacienteService.findAll(Example.of(fichaPaciente));

    if(fichaPacienteModelOptional.size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ficha não encontrada!");
    }

    return ResponseEntity.status(HttpStatus.OK).body(fichaPacienteModelOptional.get(0));
  }

  @PatchMapping("/{numeroCarteiraPlano}/{idEspecialidade}/{idPlanoSaude}")
  public ResponseEntity<Object> updateFichaPaciente(@PathVariable(name = "numeroCarteiraPlano") String numeroCarteiraPlano, @PathVariable(name = "idEspecialidade") String idEspecialidade, @PathVariable(name = "idPlanoSaude") String idPlanoSaude, @RequestBody @Valid FichaPacienteUpdateDto fichaPacienteUpdateDto ) {
    Optional<EspecialidadeModel> especialidadeModelOptional = especialidadeService.findById(UUID.fromString(idEspecialidade));

    if(!especialidadeModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrada");
    }

    Optional<PlanoDeSaudeModel> planoSaudeModelOptional = planoSaudeService.findById(UUID.fromString(idPlanoSaude));

    if(!planoSaudeModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano de saúde não encontrado");
    }

    FichaPacienteModel fichaPaciente = new FichaPacienteModel();
    fichaPaciente.setPk(new FichaPacientePK(numeroCarteiraPlano, especialidadeModelOptional.get(), planoSaudeModelOptional.get()));
    
    List<FichaPacienteModel> fichaPacienteModelOptional = fichaPacienteService.findAll(Example.of(fichaPaciente));

    if(fichaPacienteModelOptional.size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ficha não encontrada!");
    }

    BeanUtils.copyProperties(fichaPacienteModelOptional, fichaPaciente);
    fichaPaciente.setNomePaciente(fichaPacienteUpdateDto.getNomePaciente());

    return ResponseEntity.status(HttpStatus.OK).body(fichaPacienteService.salvar(fichaPaciente));
  }
}
 