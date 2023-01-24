package br.com.bruno.toshiaki.msavaliadorcredito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients()
public class MsavaliadorcreditoApplication {

  public static void main(final String[] args) {
    SpringApplication.run(MsavaliadorcreditoApplication.class, args);
  }

}
