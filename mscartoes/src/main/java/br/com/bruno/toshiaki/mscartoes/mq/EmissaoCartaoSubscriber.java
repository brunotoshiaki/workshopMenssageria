package br.com.bruno.toshiaki.mscartoes.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoSubscriber {

  @RabbitListener(queues = "${rabbitmq.queue}")
  public void receberSolicatacaoEmisaso(final String payload) {
    System.out.println("payload = " + payload);
  }


}
