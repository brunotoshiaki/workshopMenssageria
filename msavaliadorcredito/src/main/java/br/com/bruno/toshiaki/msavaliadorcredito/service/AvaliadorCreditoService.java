package br.com.bruno.toshiaki.msavaliadorcredito.service;

import static java.util.Objects.requireNonNull;

import br.com.bruno.toshiaki.msavaliadorcredito.clients.CartaoClient;
import br.com.bruno.toshiaki.msavaliadorcredito.clients.ClienteClient;
import br.com.bruno.toshiaki.msavaliadorcredito.clients.ex.DadosClientesNotFoundException;
import br.com.bruno.toshiaki.msavaliadorcredito.clients.ex.ErroComunicaoException;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.AvaliacaoClienteResponse;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.Cartao;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.CartaoAprovado;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.DadosCliente;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.SituacaoCliente;
import feign.FeignException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

  private final ClienteClient clienteClient;
  private final CartaoClient cartaoClient;



  private static CartaoAprovado getCartaoAprovado(final ResponseEntity<DadosCliente> cliente, final Cartao c) {
    final BigDecimal limiteAprovado = calcularLimite(cliente, c);
    return new CartaoAprovado(c.nome(), c.bandeira(), limiteAprovado);

  }

  private static BigDecimal calcularLimite(final ResponseEntity<DadosCliente> cliente, final Cartao c) {
    final var idade = BigDecimal.valueOf(requireNonNull(cliente.getBody()).idade());
    final var fator = idade.divide(BigDecimal.valueOf(10), RoundingMode.DOWN);
    return fator.multiply(c.limite());
  }

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

  public AvaliacaoClienteResponse realizarAvaliacao(final String cpf, final Long renda)
      throws DadosClientesNotFoundException, ErroComunicaoException {
    try {
      final var cliente = this.clienteClient.getCliente(cpf);
      final var cartoes = this.cartaoClient.getCartoesRenda(renda);

      final var result = requireNonNull(cartoes
          .getBody())
          .stream()
          .map(c -> getCartaoAprovado(cliente, c))
          .toList();

      return new AvaliacaoClienteResponse(result);
    } catch (final FeignException.FeignClientException e) {
      this.validarStatusCode(e.status());
    }
    throw new ErroComunicaoException();
  }



}
