package com.api.fichapaciente.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.fichapaciente.dtos.FichaPacienteDto;
import com.api.fichapaciente.models.FichaPacienteModel;
import com.api.fichapaciente.models.FichaPacientePK;
import com.api.fichapaciente.services.FichaPacienteService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/ficha-paciente")
public class FichaPacienteController {
  final FichaPacienteService fichaPacienteService;
  public FichaPacienteController(FichaPacienteService fichaPacienteService) {
    this.fichaPacienteService = fichaPacienteService;
  }

  @GetMapping
  public ResponseEntity<List<FichaPacienteModel>> listar() {
    return ResponseEntity.status(HttpStatus.OK).body(fichaPacienteService.findAll());
  }

  @PostMapping
  public ResponseEntity<Object> salvarFichaPaciente(@RequestBody @Valid FichaPacienteDto fichaPacienteDto){
    var fichaPacientePK = new FichaPacientePK();
    fichaPacientePK.setIdEspecialidade(fichaPacienteDto.getIdEspecialidade());
    fichaPacientePK.setIdPlanoSaude(fichaPacienteDto.getIdPlanoSaude());
    fichaPacientePK.setNumeroCarteiraPlano(fichaPacienteDto.getNumeroCarteiraPlano());

    var fichaPacienteModel = new FichaPacienteModel();
    fichaPacienteModel.setId(fichaPacientePK);
    fichaPacienteModel.setNomePaciente(fichaPacienteDto.getNomePaciente());

    return ResponseEntity.status(HttpStatus.CREATED).body(fichaPacienteService.salvar(fichaPacienteModel));
  }
}
 