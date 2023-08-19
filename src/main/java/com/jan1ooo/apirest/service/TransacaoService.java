package com.jan1ooo.apirest.service;

import com.jan1ooo.apirest.dto.TransacaoDTO;
import com.jan1ooo.apirest.model.statistics.Estatisticas;
import com.jan1ooo.apirest.model.transaction.Transacao;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransacaoService {
    private Set<Transacao> set = new HashSet<>();

    public void save(TransacaoDTO transacaoDTO){
        UUID uuid = UUID.randomUUID();
        Transacao transacao = new Transacao(uuid, transacaoDTO.valor(), transacaoDTO.dataHora(), LocalDateTime.now());
        System.out.println("Adicionado com sucesso " + transacao);
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

        /*Verifica se a transação foi feita nos ultimos 60 segundos*/
        for(Transacao t : set){
            long time = t.getHoraTransacao().until(LocalDateTime.now(), ChronoUnit.SECONDS);
            if(time < 60){
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
            for(double d: doubleStats) doubleSummaryStatistics.accept(d);
            Long count = doubleSummaryStatistics.getCount();
            Double sum = doubleSummaryStatistics.getSum();
            Double min = doubleSummaryStatistics.getMin();
            Double max = doubleSummaryStatistics.getMax();
            Double avg = doubleSummaryStatistics.getAverage();

            return new Estatisticas(count, sum, avg, min, max);
        }
    }

}
