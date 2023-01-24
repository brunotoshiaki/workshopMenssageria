package br.com.bruno.toshiaki.msavaliadorcredito.clients;

import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.CartaoCliente;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartaoClient {

  @GetMapping(params = "cpf")
  ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam final String cpf);
}
