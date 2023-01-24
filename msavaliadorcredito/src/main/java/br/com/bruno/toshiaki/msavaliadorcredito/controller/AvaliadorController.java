package br.com.bruno.toshiaki.msavaliadorcredito.controller;

import br.com.bruno.toshiaki.msavaliadorcredito.clients.ex.DadosClientesNotFoundException;
import br.com.bruno.toshiaki.msavaliadorcredito.clients.ex.ErroComunicaoException;
import br.com.bruno.toshiaki.msavaliadorcredito.service.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorController {

  private final AvaliadorCreditoService avaliadorCreditoService;

  @GetMapping
  public String status() {
    return "ok";
  }

  @GetMapping(value = "/situacao-cliente", params = "cpf")
  public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") final String cpf) {
    try {
      final var response = this.avaliadorCreditoService.obterSituacao(cpf);
      return ResponseEntity.ok().body(response);
    } catch (final DadosClientesNotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (final ErroComunicaoException e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
