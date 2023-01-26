package br.com.bruno.toshiaki.msavaliadorcredito.mq;

import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.SolicitacaoEmissaoCartao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

  private final RabbitTemplate rabbitTemplate;

  private final Queue queue;

  public void solicitarCartao(final SolicitacaoEmissaoCartao dados) throws JsonProcessingException {
    final var mapper = new ObjectMapper();
    this.rabbitTemplate.convertAndSend(this.queue.getName(), mapper.writeValueAsString(dados));
  }

}
