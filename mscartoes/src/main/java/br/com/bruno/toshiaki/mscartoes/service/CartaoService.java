package br.com.bruno.toshiaki.mscartoes.service;

import br.com.bruno.toshiaki.mscartoes.domain.Cartao;
import br.com.bruno.toshiaki.mscartoes.repository.CartaoRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartaoService {

  private final CartaoRepository repository;

  @Transactional
  public void save(final Cartao cartao) {
     this.repository.save(cartao);
  }

  public List<Cartao> getCartaoesRendaMenorIgual(final Long renda) {
    final var rendaBigDecimal = BigDecimal.valueOf(renda);
    return this.repository.findByRendaLessThanEqual(rendaBigDecimal);
  }

}
