package com.api.fichapaciente.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.fichapaciente.dtos.PlanoSaudeDto;
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
}
