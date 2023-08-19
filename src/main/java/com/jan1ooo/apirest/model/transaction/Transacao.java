package com.jan1ooo.apirest.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "dataHora")
public class Transacao implements Comparable<Transacao>{

    private UUID id_transacao;

    @Positive
    private Double valor;

    @Past
    private LocalDateTime dataHora;

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
