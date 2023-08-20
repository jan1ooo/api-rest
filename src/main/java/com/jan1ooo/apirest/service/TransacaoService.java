package com.jan1ooo.apirest.service;

import com.jan1ooo.apirest.dto.TransacaoDTO;
import com.jan1ooo.apirest.exception.BodyBadRequestException;
import com.jan1ooo.apirest.exception.BodyUnprocessableEntityException;
import com.jan1ooo.apirest.model.statistics.Estatisticas;
import com.jan1ooo.apirest.model.transaction.Transacao;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransacaoService {
    private Set<Transacao> set = new HashSet<>();
    private final Logger logger = LoggerFactory.getLogger(TransacaoService.class);

    public void save(@Valid TransacaoDTO transacaoDTO){
        if(transacaoDTO.valor() == null || transacaoDTO.dataHora() == null){
            logger.error("JSON Inválido na requisição: POST /transacao");
            throw new BodyBadRequestException("A API não compreendeu a requisição do cliente (por exemplo: um JSON inválido)");
        }

        if(transacaoDTO.valor() < 0){
            logger.error("O valor informado é inválido na requisição: POST /transacao");
            throw new BodyUnprocessableEntityException("valor precisa ser maior ou igual a zero!");
        }

        if(LocalDateTime.now().isBefore(transacaoDTO.dataHora())){
            logger.error("A data informado é inválida na requisição: POST /transacao");
            throw new BodyUnprocessableEntityException("data não pode ser futura!");
        }

        UUID uuid = UUID.randomUUID();
        Transacao transacao = new Transacao(uuid, transacaoDTO.valor(), transacaoDTO.dataHora(), LocalDateTime.now());
        logger.info("Criando transação: " + transacao.getId_transacao());
        set.add(transacao);
    }

    public Set<Transacao> findAll(){
        logger.info("Listando transações");
        return set;
    }

    public void delete(){
        logger.warn("Todas as transações foram deletadas");
        set.clear();
    }

    public Estatisticas estatisticas(){
        Set<Transacao> list = new HashSet<>();

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
            Long count = doubleSummaryStatistics.getCount();
            Double sum = doubleSummaryStatistics.getSum();
            Double min = doubleSummaryStatistics.getMin();
            Double max = doubleSummaryStatistics.getMax();
            Double avg = doubleSummaryStatistics.getAverage();
            logger.info("Listando as estatisticas");
            return new Estatisticas(count, sum, avg, min, max);
        }
    }

}
