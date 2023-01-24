package br.com.bruno.toshiaki.msavaliadorcredito.clients;

import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteClient {

  @GetMapping(params = "cpf")
  ResponseEntity<DadosCliente> getCliente(@RequestParam final String cpf);
}
