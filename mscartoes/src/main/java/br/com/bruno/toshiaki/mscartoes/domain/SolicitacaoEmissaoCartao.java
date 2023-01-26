package br.com.bruno.toshiaki.mscartoes.domain;

import java.math.BigDecimal;

public record SolicitacaoEmissaoCartao(Long id, String cpf, String endereco, BigDecimal limite) {

}
