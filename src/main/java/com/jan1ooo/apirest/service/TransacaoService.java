package com.jan1ooo.apirest.service;

import com.jan1ooo.apirest.dto.TransacaoDTO;
import com.jan1ooo.apirest.exception.BodyBadRequestException;
import com.jan1ooo.apirest.exception.BodyUnprocessableEntityException;
import com.jan1ooo.apirest.model.statistics.Estatisticas;
import com.jan1ooo.apirest.model.transaction.Transacao;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransacaoService {
    private Set<Transacao> set = new HashSet<>();

    public void save(@Valid TransacaoDTO transacaoDTO){
        if(transacaoDTO.valor() == null || transacaoDTO.dataHora() == null){
            throw new BodyBadRequestException("A API não compreendeu a requisição do cliente (por exemplo: um JSON inválido)");
        }

        if(transacaoDTO.valor() < 0){
            throw new BodyUnprocessableEntityException("valor precisa ser maior ou igual a zero!");
        }

        if(LocalDateTime.now().isBefore(transacaoDTO.dataHora())){
            throw new BodyUnprocessableEntityException("data não pode ser futura!");
        }

        UUID uuid = UUID.randomUUID();
        Transacao transacao = new Transacao(uuid, transacaoDTO.valor(), transacaoDTO.dataHora(), LocalDateTime.now());
        set.add(transacao);
    }

    public Set<Transacao> findAll(){
        return set;
    }

    public void delete(){
        set.clear();
    }

    public Estatisticas estatisticas(){
        Set<Transacao> list = new HashSet<>();

        /*Long count = 0L;*/
        /*Verifica se a transação foi feita nos ultimos 60 segundos*/
        for(Transacao t : set){
            long time = t.getHoraTransacao().until(LocalDateTime.now(), ChronoUnit.SECONDS);
            if(time < 60){
                /*count++;*/
                list.add(t);
            }
        }

        if(list.isEmpty()){
            return new Estatisticas(0L, 0.0, 0.0, 0.0, 0.0);
        }else{

            List<Double> doubleStats = new ArrayList<>();
            for(Transacao t : list){
                doubleStats.add(t.getValor());
            }

            DoubleSummaryStatistics doubleSummaryStatistics = doubleStats.stream().collect(Collectors.summarizingDouble(e -> e));
            /*for(double d: doubleStats) doubleSummaryStatistics.accept(d);*/
            Long count = doubleSummaryStatistics.getCount();
            Double sum = doubleSummaryStatistics.getSum();
            Double min = doubleSummaryStatistics.getMin();
            Double max = doubleSummaryStatistics.getMax();
            Double avg = doubleSummaryStatistics.getAverage();

            return new Estatisticas(count, sum, avg, min, max);
        }
    }

}
