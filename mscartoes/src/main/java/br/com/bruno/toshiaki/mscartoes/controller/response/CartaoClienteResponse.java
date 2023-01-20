package br.com.bruno.toshiaki.mscartoes.controller.response;

import br.com.bruno.toshiaki.mscartoes.domain.BandeiraCartao;
import java.math.BigDecimal;

public record CartaoClienteResponse(String nome, BandeiraCartao bandeira, BigDecimal limite) {

}
