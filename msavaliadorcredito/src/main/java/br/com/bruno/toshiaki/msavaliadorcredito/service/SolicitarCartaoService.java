package br.com.bruno.toshiaki.msavaliadorcredito.service;

import br.com.bruno.toshiaki.msavaliadorcredito.clients.ex.ErroComunicaoException;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.ProtocoloSolicitacao;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.SolicitacaoEmissaoCartao;
import br.com.bruno.toshiaki.msavaliadorcredito.mq.SolicitacaoEmissaoCartaoPublisher;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolicitarCartaoService {

  private final SolicitacaoEmissaoCartaoPublisher publisher;


  public ProtocoloSolicitacao solicitarEmissaoCartao(final SolicitacaoEmissaoCartao dados) throws ErroComunicaoException {
    try {
      this.publisher.solicitarCartao(dados);
      return new ProtocoloSolicitacao(UUID.randomUUID().toString());

    } catch (final Exception e) {
      throw new ErroComunicaoException();
    }

  }

}
