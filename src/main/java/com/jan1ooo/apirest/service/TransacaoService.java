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
import java.util.*;

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

        Integer count = 0;
        Double sumCalc = 0.0;
        Double min = 0.0;
        Double max = 0.0;

        for(Transacao t : set){
            count++;
            min = t.getValor();
            if(min > t.getValor()){
                min = t.getValor();
            }

            if(t.getValor() > max){
                max = t.getValor();
            }
            sumCalc += t.getValor();
            System.out.println(t);
        }
        /*Alterando para BIGDECIMAL para conseguir formatar*/
        BigDecimal avgBD = new BigDecimal(sumCalc / count);
        BigDecimal sumBD = new BigDecimal(sumCalc);

        /*Formatando as casas decimais do soma e média*/
        BigDecimal avg = avgBD.setScale(3, RoundingMode.FLOOR);
        BigDecimal sum = sumBD.setScale(2, RoundingMode.FLOOR);

        return new Estatisticas(count, sum, avg, min, max);
    }

}

