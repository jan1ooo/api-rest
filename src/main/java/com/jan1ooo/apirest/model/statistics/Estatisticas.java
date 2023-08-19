package com.jan1ooo.apirest.model.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estatisticas {

    private Long count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;
}
