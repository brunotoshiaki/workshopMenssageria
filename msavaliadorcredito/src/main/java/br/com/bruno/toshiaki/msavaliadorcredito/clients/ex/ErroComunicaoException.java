package br.com.bruno.toshiaki.msavaliadorcredito.clients.ex;

public class ErroComunicaoException extends Exception{

  public ErroComunicaoException() {
    super("Não foi possivel comunicar com os serviços");
  }
}
