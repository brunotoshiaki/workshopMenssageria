package br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model;

import java.math.BigDecimal;

public record Cartao(Long id, String nome, String bandeira, BigDecimal limite) {

}
