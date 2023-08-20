package com.jan1ooo.apirest.dto;

import java.time.OffsetDateTime;

public record TransacaoDTO(Double valor,
                           OffsetDateTime dataHora) {
}
