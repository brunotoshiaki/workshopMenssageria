package br.com.bruno.toshiaki.mscartoes.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

  @Value("${rabbitmq.queue}")
  private String queueName;


  @Bean
  public Queue myQueue() {
    return new Queue(this.queueName);
  }



}
