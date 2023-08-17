package com.jan1ooo.apirest.model.transaction;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "dataHora")
public class Transacao{

    @Positive
    private BigDecimal valor;

    @Past
    private LocalDateTime dataHora;

    @Override
    public String toString() {
        return "Transacao{" +
                "valor=" + valor +
                ", dataHora=" + dataHora +
                '}';
    }
}
