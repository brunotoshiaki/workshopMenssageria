package br.com.bruno.toshiaki.mscartoes.controller;

import br.com.bruno.toshiaki.mscartoes.controller.mapper.CartaoMapper;
import br.com.bruno.toshiaki.mscartoes.controller.request.CartaoSaveRequest;
import br.com.bruno.toshiaki.mscartoes.domain.Cartao;
import br.com.bruno.toshiaki.mscartoes.service.CartaoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartoesController {

  private final CartaoService service;

  @GetMapping
  public String status() {
    return "ok";
  }

  @PostMapping
  public ResponseEntity cadastra(@RequestBody final CartaoSaveRequest request) {
    final var cartao = CartaoMapper.INSTANCE.toEntity(request);
    this.service.save(cartao);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(params = "renda")
  public ResponseEntity<List<Cartao>> getCartoesRenda(@RequestParam("renda") final Long renda) {
    final var cartoes = this.service.getCartaoesRendaMenorIgual(renda);
    return ResponseEntity.ok(cartoes);
  }

}
