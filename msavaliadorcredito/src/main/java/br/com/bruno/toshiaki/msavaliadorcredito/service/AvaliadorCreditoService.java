package br.com.bruno.toshiaki.msavaliadorcredito.service;

import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.CartaoCliente;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.SituacaoCliente;
import br.com.bruno.toshiaki.msavaliadorcredito.clients.ClienteClient;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

  private final ClienteClient clienteClient;

  public SituacaoCliente obterSituacao(final String cpf) {
    final var result = this.clienteClient.getCliente(cpf);

    return new SituacaoCliente(result.getBody(), List.of(new CartaoCliente("carlos", "visa", new BigDecimal(20000))));
  }
}
