package br.com.bruno.toshiaki.msavaliadorcredito.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solicitar-cartao")
@RequiredArgsConstructor
public class SoliciatacaoCartaoController {


  @GetMapping
  public String status() {
    return "ok";
  }


}
