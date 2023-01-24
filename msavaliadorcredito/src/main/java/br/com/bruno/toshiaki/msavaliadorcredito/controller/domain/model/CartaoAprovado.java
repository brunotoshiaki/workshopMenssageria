package br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model;

import java.math.BigDecimal;

public record CartaoAprovado(String cartao, String bandeira, BigDecimal limiteAprovado ) {

}
