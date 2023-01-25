package br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model;

import java.math.BigDecimal;

public record SolicitacaoEmissaoCartao(Long id, String cpf, String endereco, BigDecimal limete) {

}
