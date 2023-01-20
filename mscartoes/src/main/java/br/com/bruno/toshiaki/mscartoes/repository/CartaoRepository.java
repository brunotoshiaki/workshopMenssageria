package br.com.bruno.toshiaki.mscartoes.repository;

import br.com.bruno.toshiaki.mscartoes.domain.Cartao;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

  List<Cartao> findByRendaLessThanEqual(final BigDecimal renda);


}
