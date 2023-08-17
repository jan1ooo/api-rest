package com.jan1ooo.apirest.service;

import com.jan1ooo.apirest.dto.TransacaoDTO;
import com.jan1ooo.apirest.model.transaction.Transacao;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransacaoService {
    private Set<Transacao> set = new HashSet<>();

    public void save(TransacaoDTO transacaoDTO){
        Transacao transacao = new Transacao(transacaoDTO.valor(), transacaoDTO.dataHora());
        System.out.println("Adicionado com sucesso " + transacao);
        set.add(transacao);
    }

    public Set<Transacao> findAll(){
        return set;
    }

}

