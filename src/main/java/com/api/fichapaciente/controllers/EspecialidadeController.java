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

import com.api.fichapaciente.dtos.EspecialidadeDto;
import com.api.fichapaciente.models.EspecialidadeModel;
import com.api.fichapaciente.services.EspecialidadeService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/especialidade")
public class EspecialidadeController {
  final EspecialidadeService especialidadeService;

  public EspecialidadeController(EspecialidadeService especialidadeService) {
    this.especialidadeService = especialidadeService;
  }

  @GetMapping
  public ResponseEntity<List<EspecialidadeModel>> listaEspecialidades() {
    return ResponseEntity.status(HttpStatus.OK).body(especialidadeService.findAll());
  }

  @PostMapping
  public ResponseEntity<Object> salvarEspecialidade(@RequestBody @Valid EspecialidadeDto especialidadeDto) {
    var especialidadeModel = new EspecialidadeModel();

    BeanUtils.copyProperties(especialidadeDto, especialidadeModel);

    return ResponseEntity.status(HttpStatus.CREATED).body(especialidadeService.salvar(especialidadeModel));
  }
}
