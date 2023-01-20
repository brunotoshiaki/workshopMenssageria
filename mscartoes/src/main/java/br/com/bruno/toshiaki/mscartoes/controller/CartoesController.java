package br.com.bruno.toshiaki.mscartoes.controller;

import br.com.bruno.toshiaki.mscartoes.controller.mapper.CartaoMapper;
import br.com.bruno.toshiaki.mscartoes.controller.mapper.ClienteCartaoMapper;
import br.com.bruno.toshiaki.mscartoes.controller.request.CartaoSaveRequest;
import br.com.bruno.toshiaki.mscartoes.controller.request.ClienteCartaoRequest;
import br.com.bruno.toshiaki.mscartoes.controller.response.CartaoClienteResponse;
import br.com.bruno.toshiaki.mscartoes.domain.Cartao;
import br.com.bruno.toshiaki.mscartoes.service.CartaoService;
import br.com.bruno.toshiaki.mscartoes.service.ClienteCartaoService;
import java.util.List;
import java.util.stream.Collectors;
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

  private final CartaoService cartaoService;
  private final ClienteCartaoService clienteCartaoService;

  @GetMapping
  public String status() {
    return "ok";
  }

  @PostMapping
  public ResponseEntity saveCartao(@RequestBody final CartaoSaveRequest request) {
    final var cartao = CartaoMapper.INSTANCE.toEntity(request);
    this.cartaoService.save(cartao);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/clienteCartao")
  public ResponseEntity salvarClienteCartao(@RequestBody final ClienteCartaoRequest request) {
    final var cartao = ClienteCartaoMapper.toModel(request);
    this.clienteCartaoService.save(cartao);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(params = "renda")
  public ResponseEntity<List<Cartao>> getCartoesRenda(@RequestParam("renda") final Long renda) {
    final var cartoes = this.cartaoService.getCartaoesRendaMenorIgual(renda);
    return ResponseEntity.ok(cartoes);
  }

  @GetMapping(params = "cpf")
  public ResponseEntity<List<CartaoClienteResponse>> getCartoesByCliente(@RequestParam final String cpf) {
    final var cartoes = this.clienteCartaoService.listCartoesByCpf(cpf);
    final var response = cartoes
        .stream()
        .map(ClienteCartaoMapper::fromModel).collect(Collectors.toList());
    return ResponseEntity.ok(response);
  }

}
