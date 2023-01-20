package br.com.bruno.toshiaki.mscartoes.controller.request;

import br.com.bruno.toshiaki.mscartoes.domain.BandeiraCartao;
import java.math.BigDecimal;

public record CartaoSaveRequest(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limite) {

}
