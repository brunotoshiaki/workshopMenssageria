package br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model;

import java.util.List;


public record SituacaoCliente(DadosCliente cliente, List<CartaoCliente> cartoes) {

}
