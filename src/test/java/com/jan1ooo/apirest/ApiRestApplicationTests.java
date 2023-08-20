package com.jan1ooo.apirest;

import com.jan1ooo.apirest.dto.TransacaoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;

@SpringBootTest
class ApiRestApplicationTests {

	@Test
	void verificaSeTodosOsCamposEstaoPreenchidos(){
		TransacaoDTO transacao = new TransacaoDTO(290.0, OffsetDateTime.parse("2022-08-07T12:34:56.789-03:00"));
		Assertions.assertNotNull(transacao);
	}

	@Test
	void verificaSeCamposEstaoVazios(){
		TransacaoDTO transacaoDTO = null;
		Assertions.assertNull(transacaoDTO);
	}

	@Test
	void verificaValorPositivoOuZero(){
		TransacaoDTO transacao = new TransacaoDTO(3190.0, OffsetDateTime.parse("2021-04-07T12:34:56.789-03:00"));
		Assertions.assertTrue(transacao.valor() >= 0);
	}

	@Test
	void verificaValorNegativo(){
		TransacaoDTO transacao = new TransacaoDTO(-123.4, OffsetDateTime.parse("2020-02-07T14:34:56.789-03:00"));
		Assertions.assertTrue(transacao.valor() < 0);
	}

	@Test
	void verificaDataFuturo(){
		TransacaoDTO transacao = new TransacaoDTO(2900.90, OffsetDateTime.parse("2024-02-10T11:23:43.721-03:00"));
		Assertions.assertTrue(transacao.dataHora().isAfter(OffsetDateTime.now()));
	}

	@Test
	void verificaDataPassada(){
		TransacaoDTO transacao = new TransacaoDTO(133.03, OffsetDateTime.parse("2021-01-12T11:23:43.721-03:00"));
		Assertions.assertTrue(transacao.dataHora().isBefore(OffsetDateTime.now()));
	}
}
