package com.api.fichapaciente.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.fichapaciente.dtos.PlanoSaudeDto;
import com.api.fichapaciente.dtos.PlanoSaudeUpdateDto;
import com.api.fichapaciente.models.PlanoDeSaudeModel;
import com.api.fichapaciente.services.PlanoSaudeService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/plano-saude")
public class PlanoSaudeController {
  final PlanoSaudeService planoSaudeService;

  public PlanoSaudeController(PlanoSaudeService planoSaudeService) {
    this.planoSaudeService = planoSaudeService;
  }

  @GetMapping
  public ResponseEntity<List<PlanoDeSaudeModel>> listar() {
    return ResponseEntity.status(HttpStatus.OK).body(planoSaudeService.findAll());
  }

  @PostMapping
  public ResponseEntity<Object> salvar(@RequestBody @Valid PlanoSaudeDto planoSaudeDto) {
    var planoDeSaudeModel = new PlanoDeSaudeModel();

    BeanUtils.copyProperties(planoSaudeDto, planoDeSaudeModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(planoSaudeService.salvar(planoDeSaudeModel));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Object> updatePlanoSaude(@PathVariable(name = "id") String id, @RequestBody @Valid PlanoSaudeUpdateDto planoSaudeUpdateDto) {
    Optional<PlanoDeSaudeModel> planoSaudeModelOptional = planoSaudeService.findById(UUID.fromString(id));

    if(!planoSaudeModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano de saúde não encontrado");
    }

    PlanoDeSaudeModel planoDeSaudeModel = planoSaudeModelOptional.get();
    planoDeSaudeModel.setNome(planoSaudeUpdateDto.getNome());

    return ResponseEntity.status(HttpStatus.OK).body(planoSaudeService.salvar(planoDeSaudeModel));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deletePlanoSaude(@PathVariable(name = "id") String id){
    try {
      Optional<PlanoDeSaudeModel> planoSaudeModelOptional = planoSaudeService.findById(UUID.fromString(id));

      if(!planoSaudeModelOptional.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano de saúde não encontrado");
      }

      planoSaudeService.deletePlanoSaude(planoSaudeModelOptional.get());
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    } catch(Exception exc) {
      if(exc instanceof DataIntegrityViolationException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existe uma ficha de paciente cadastrada para esse plano de saúde");
      }else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exc);
      }
    }
  }
}
