package br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model;

import java.math.BigDecimal;

public record CartaoCliente(String nome, String bandeira, BigDecimal limite) {

}
