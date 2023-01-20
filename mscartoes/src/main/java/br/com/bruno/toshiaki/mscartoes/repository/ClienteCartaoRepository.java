package br.com.bruno.toshiaki.mscartoes.repository;

import br.com.bruno.toshiaki.mscartoes.domain.ClienteCartao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {

  List<ClienteCartao> findByCpf(final String cpf);




}
