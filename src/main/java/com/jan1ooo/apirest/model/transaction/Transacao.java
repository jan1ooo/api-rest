package com.jan1ooo.apirest.model.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "dataHora")
public class Transacao implements Comparable<Transacao>{

    private UUID id_transacao;

    @PositiveOrZero
    private Double valor;

    @Past
    private OffsetDateTime dataHora;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime horaTransacao;

    @Override
    public String toString() {
        return "Transacao{" +
                "valor=" + valor +
                ", dataHora=" + dataHora +
                '}';
    }

    @Override
    public int compareTo(Transacao t) {
        return valor.compareTo(t.getValor());
    }
}
