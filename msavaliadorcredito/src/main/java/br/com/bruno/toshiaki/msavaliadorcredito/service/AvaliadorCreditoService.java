package br.com.bruno.toshiaki.msavaliadorcredito.service;

import br.com.bruno.toshiaki.msavaliadorcredito.clients.CartaoClient;
import br.com.bruno.toshiaki.msavaliadorcredito.clients.ClienteClient;
import br.com.bruno.toshiaki.msavaliadorcredito.clients.ex.DadosClientesNotFoundException;
import br.com.bruno.toshiaki.msavaliadorcredito.clients.ex.ErroComunicaoException;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.SituacaoCliente;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

  private final ClienteClient clienteClient;
  private final CartaoClient cartaoClient;

  public SituacaoCliente obterSituacao(final String cpf) throws DadosClientesNotFoundException, ErroComunicaoException {
    try {
      final var cliente = this.clienteClient.getCliente(cpf);
      final var cartao = this.cartaoClient.getCartoesByCliente(cpf);

      return new SituacaoCliente(cliente.getBody(), cartao.getBody());
    } catch (final FeignException.FeignClientException e) {
      this.validarStatusCode(e.status());
    }
    throw new ErroComunicaoException();
  }

  private void validarStatusCode(final int status) throws DadosClientesNotFoundException {
    if (HttpStatus.NOT_FOUND.value() == status) {
      throw new DadosClientesNotFoundException();
    }

  }
}
