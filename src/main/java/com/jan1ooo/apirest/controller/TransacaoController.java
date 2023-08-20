package com.jan1ooo.apirest.controller;

import com.jan1ooo.apirest.dto.TransacaoDTO;
import com.jan1ooo.apirest.model.statistics.Estatisticas;
import com.jan1ooo.apirest.model.transaction.Transacao;
import com.jan1ooo.apirest.service.TransacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "Transação", description = "API Transação")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping("/transacao")
    public ResponseEntity<Void> save(@Valid @RequestBody TransacaoDTO transacaoDTO){
        service.save(transacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/transacao")
    public ResponseEntity<Set<Transacao>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<Void> deleteAll(){
        service.delete();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<Estatisticas> estatisticas(){
        return ResponseEntity.ok().body(service.estatisticas());
    }
}
