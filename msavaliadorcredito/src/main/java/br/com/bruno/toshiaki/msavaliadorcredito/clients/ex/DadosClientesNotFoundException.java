package br.com.bruno.toshiaki.msavaliadorcredito.clients.ex;

public class DadosClientesNotFoundException extends Exception {

  public DadosClientesNotFoundException() {
    super("Dados do cliente não encontrados");
  }
}
