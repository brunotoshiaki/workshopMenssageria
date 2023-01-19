package br.com.bruno.toshiaki.clientmicroserver.controller;

import br.com.bruno.toshiaki.clientmicroserver.controller.mapper.ClienteMapper;
import br.com.bruno.toshiaki.clientmicroserver.controller.request.ClienteSaveRequest;
import br.com.bruno.toshiaki.clientmicroserver.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

  private final ClienteService service;

  @GetMapping
  public String status() {
    return "ok";
  }

  @PostMapping

  public ResponseEntity save(@RequestBody final ClienteSaveRequest request) {
    final var cliente = ClienteMapper.INSTANCE.toEntity(request);
    this.service.save(cliente);

    final var uri = ServletUriComponentsBuilder
        .fromCurrentRequestUri()
        .query("cpf={cpf}")
        .buildAndExpand(cliente.getCpf())
        .toUri();

    return ResponseEntity.created(uri).build();
  }

  @GetMapping(params = "cpf")
  public ResponseEntity getCliente(@RequestParam final String cpf) {
    final var cliente = this.service.getByCpf(cpf);

    if (cliente.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(cliente);
  }
}
