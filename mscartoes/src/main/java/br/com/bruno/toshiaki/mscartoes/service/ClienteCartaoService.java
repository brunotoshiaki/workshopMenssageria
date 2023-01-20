package br.com.bruno.toshiaki.mscartoes.service;

import br.com.bruno.toshiaki.mscartoes.domain.ClienteCartao;
import br.com.bruno.toshiaki.mscartoes.repository.CartaoRepository;
import br.com.bruno.toshiaki.mscartoes.repository.ClienteCartaoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

  private final ClienteCartaoRepository repository;
  private final CartaoRepository cartaoRepository;

  @Transactional
  public void save(final ClienteCartao cartao) {
    this.cartaoRepository.save(cartao.getCartao());

    this.repository.save(cartao);
  }

  public List<ClienteCartao> listCartoesByCpf(final String cpf) {
    return this.repository.findByCpf(cpf);
  }

}
