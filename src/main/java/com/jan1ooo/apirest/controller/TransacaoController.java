package com.jan1ooo.apirest.controller;

import com.jan1ooo.apirest.dto.TransacaoDTO;
import com.jan1ooo.apirest.model.transaction.Transacao;
import com.jan1ooo.apirest.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TransacaoDTO transacaoDTO){
        service.save(transacaoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<Transacao>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
