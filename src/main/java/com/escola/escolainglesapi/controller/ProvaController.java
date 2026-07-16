package com.escola.escolainglesapi.controller;

import com.escola.escolainglesapi.dto.DadosCadastroProvaDTO;
import com.escola.escolainglesapi.dto.ProvaDTO;
import com.escola.escolainglesapi.service.ProvaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provas")
public class ProvaController {

    private final ProvaService provaService;

    public ProvaController(ProvaService provaService) {
        this.provaService = provaService;
    }

    @PostMapping
    public ResponseEntity<ProvaDTO> cadastrar(@RequestBody @Valid DadosCadastroProvaDTO dados) {
        ProvaDTO prova = provaService.criar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(prova);
    }

    @GetMapping
    public ResponseEntity<List<ProvaDTO>> listar() {
        List<ProvaDTO> provas = provaService.listarTodas();
        return ResponseEntity.ok(provas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvaDTO> buscarPorId(@PathVariable Long id) {
        ProvaDTO prova = provaService.buscarPorId(id);
        return ResponseEntity.ok(prova);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProvaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroProvaDTO dados) {
        ProvaDTO prova = provaService.atualizar(id, dados);
        return ResponseEntity.ok(prova);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        provaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}