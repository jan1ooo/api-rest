package com.jan1ooo.apirest.dto;

import java.time.LocalDateTime;

public record TransacaoDTO(Double valor,
                           LocalDateTime dataHora) {
}
