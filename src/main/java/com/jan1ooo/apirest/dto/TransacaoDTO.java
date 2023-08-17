package com.jan1ooo.apirest.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDTO(@Positive
                           BigDecimal valor,
                           @Past
                           LocalDateTime dataHora) {
}
