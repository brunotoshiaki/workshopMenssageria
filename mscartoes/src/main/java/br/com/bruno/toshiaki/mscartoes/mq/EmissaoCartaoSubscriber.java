package br.com.bruno.toshiaki.mscartoes.mq;

import static br.com.bruno.toshiaki.mscartoes.domain.ClienteCartao.criarClienteCartao;

import br.com.bruno.toshiaki.mscartoes.domain.SolicitacaoEmissaoCartao;
import br.com.bruno.toshiaki.mscartoes.repository.CartaoRepository;
import br.com.bruno.toshiaki.mscartoes.repository.ClienteCartaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {


  private final CartaoRepository cartaoRepository;
  private final ClienteCartaoRepository clienteCartaoRepository;

  @RabbitListener(queues = "${rabbitmq.queue}")
  public void receberSolicatacaoEmisaso(@Payload final String payload) {
    try {
      final var mapper = new ObjectMapper();
      final var dados = mapper.readValue(payload, SolicitacaoEmissaoCartao.class);
      this.gerarCartao(dados);
    } catch (final Exception ex) {
      ex.printStackTrace();
    }
  }

  private void gerarCartao(final SolicitacaoEmissaoCartao dados) {
    final var cartao = this.cartaoRepository.findById(dados.id()).orElseThrow();
    final var clienteCartao = criarClienteCartao(cartao, dados.cpf(), dados.limite());
    this.clienteCartaoRepository.save(clienteCartao);

  }


}
