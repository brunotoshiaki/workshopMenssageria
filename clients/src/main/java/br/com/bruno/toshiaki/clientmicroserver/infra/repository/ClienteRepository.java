package br.com.bruno.toshiaki.clientmicroserver.infra.repository;

import br.com.bruno.toshiaki.clientmicroserver.domain.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  Optional<Cliente> findByCpf(final String cpf);

}
