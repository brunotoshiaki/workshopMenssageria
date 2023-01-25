package br.com.bruno.toshiaki.msavaliadorcredito.mq;

import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.SolicitacaoEmissaoCartao;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

  private final Gson json = new Gson();


  private final RabbitTemplate rabbitTemplate;

  private final Queue queue;

  public void solicitarCartao(final SolicitacaoEmissaoCartao dados) {
    this.rabbitTemplate.convertAndSend(this.queue.getName(), this.json.toJson(dados));

  }

}
