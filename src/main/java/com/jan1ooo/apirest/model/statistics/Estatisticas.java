package com.jan1ooo.apirest.model.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estatisticas {

    private Integer count;
    private BigDecimal sum;
    private BigDecimal avg;
    private Double min;
    private Double max;
}
