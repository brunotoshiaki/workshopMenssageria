package br.com.bruno.toshiaki.msavaliadorcredito.service;

import br.com.bruno.toshiaki.msavaliadorcredito.clients.CartaoClient;
import br.com.bruno.toshiaki.msavaliadorcredito.clients.ClienteClient;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

  private final ClienteClient clienteClient;
  private final CartaoClient cartaoClient;

  public SituacaoCliente obterSituacao(final String cpf) {
    final var cliente = this.clienteClient.getCliente(cpf);
    final var cartao = this.cartaoClient.getCartoesByCliente(cpf);

    return new SituacaoCliente(cliente.getBody(), cartao.getBody());
  }
}
