package br.com.bruno.toshiaki.msavaliadorcredito.controller;

import br.com.bruno.toshiaki.msavaliadorcredito.clients.ex.ErroComunicaoException;
import br.com.bruno.toshiaki.msavaliadorcredito.controller.domain.model.SolicitacaoEmissaoCartao;
import br.com.bruno.toshiaki.msavaliadorcredito.service.SolicitarCartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicatao-cartao")
@RequiredArgsConstructor
public class SoliciatacaoCartaoController {

  private final SolicitarCartaoService solicitarCartao;

  @PostMapping()
  public ResponseEntity solicitarCartao(@RequestBody final SolicitacaoEmissaoCartao dados) {
    try {
      final var protocolo = this.solicitarCartao.solicitarEmissaoCartao(dados);
      return ResponseEntity.ok(protocolo);
    } catch (final ErroComunicaoException e) {
      return ResponseEntity.badRequest().build();
    }
  }

}
