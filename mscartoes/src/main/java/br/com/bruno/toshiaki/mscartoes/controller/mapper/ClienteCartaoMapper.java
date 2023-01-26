package br.com.bruno.toshiaki.mscartoes.controller.mapper;

import static br.com.bruno.toshiaki.mscartoes.domain.ClienteCartao.criarClienteCartao;

import br.com.bruno.toshiaki.mscartoes.controller.request.ClienteCartaoRequest;
import br.com.bruno.toshiaki.mscartoes.controller.response.CartaoClienteResponse;
import br.com.bruno.toshiaki.mscartoes.domain.ClienteCartao;


public class ClienteCartaoMapper {


  public static CartaoClienteResponse fromModel(final ClienteCartao request) {
    return new CartaoClienteResponse(request.getCartao().getNome(), request.getCartao().getBandeira(), request.getCartao().getLimite());
  }

  public static ClienteCartao toModel(final ClienteCartaoRequest request) {
    final var cartao = CartaoMapper.INSTANCE.toEntity(request.cartao());

    return criarClienteCartao(cartao, request.cpf(), request.cartao().limite());
  }


}
