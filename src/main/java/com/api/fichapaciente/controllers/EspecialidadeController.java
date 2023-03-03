package com.api.fichapaciente.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.api.fichapaciente.dtos.EspecialidadeDto;
import com.api.fichapaciente.dtos.EspecialidadeUpdateDto;
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

  @GetMapping("/{id}")
  public ResponseEntity<Object> getEspecialidadeById(@PathVariable(name = "id") String id) {
    Optional<EspecialidadeModel> especialidadeModelOptional = especialidadeService.findById(UUID.fromString(id));

    if(!especialidadeModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrada");
    }

    return ResponseEntity.status(HttpStatus.OK).body(especialidadeModelOptional.get());
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Object> updateEspecialidade(@PathVariable(name = "id") String id, @RequestBody EspecialidadeUpdateDto especialidadeUpdateDto) {
    Optional<EspecialidadeModel> especialidadeModelOptional = especialidadeService.findById(UUID.fromString(id));
    
    if(!especialidadeModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrada");
    }
    
    var especialidadeModel = new EspecialidadeModel();
    BeanUtils.copyProperties(especialidadeModelOptional.get(), especialidadeModel);
    especialidadeModel.setNome(especialidadeUpdateDto.getNome());

    return ResponseEntity.status(HttpStatus.OK).body(especialidadeService.salvar(especialidadeModel));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteEspecialidade(@PathVariable(name = "id") String id) {
    Optional<EspecialidadeModel> especialidadeModelOptional = especialidadeService.findById(UUID.fromString(id));

    if(!especialidadeModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade não encontrada");
    }

    especialidadeService.deleteEspecialidade(especialidadeModelOptional.get());
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
  }
}
