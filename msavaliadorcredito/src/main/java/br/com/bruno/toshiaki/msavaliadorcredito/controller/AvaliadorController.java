package br.com.bruno.toshiaki.msavaliadorcredito.controller;

import br.com.bruno.toshiaki.msavaliadorcredito.clients.ex.DadosClientesNotFoundException;
import br.com.bruno.toshiaki.msavaliadorcredito.clients.ex.ErroComunicaoException;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.DadosAvaliacao;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.SolicitacaoEmissaoCartao;
import br.com.bruno.toshiaki.msavaliadorcredito.service.AvaliadorCreditoService;
import br.com.bruno.toshiaki.msavaliadorcredito.service.SolicitarCartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorController {

  private final AvaliadorCreditoService avaliadorCreditoService;
  private final SolicitarCartaoService solicitarCartao;

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

  @PostMapping
  public ResponseEntity realizarAvaliacao(@RequestBody final DadosAvaliacao dados) {

    try {
      final var response = this.avaliadorCreditoService.realizarAvaliacao(dados.cpf(), dados.renda());
      return ResponseEntity.ok().body(response);
    } catch (final DadosClientesNotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (final ErroComunicaoException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/solicitar-cartao")
  public ResponseEntity solicitarCartao(@RequestBody final SolicitacaoEmissaoCartao dados) {
    try {
      final var protocolo = this.solicitarCartao.solicitarEmissaoCartao(dados);
      return ResponseEntity.ok(protocolo);
    } catch (final ErroComunicaoException e) {
      return ResponseEntity.badRequest().build();
    }
  }

}
