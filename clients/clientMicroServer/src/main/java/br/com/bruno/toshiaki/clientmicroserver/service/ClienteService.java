package br.com.bruno.toshiaki.clientmicroserver.service;

import br.com.bruno.toshiaki.clientmicroserver.domain.Cliente;
import br.com.bruno.toshiaki.clientmicroserver.infra.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

  private final ClienteRepository repository;

  @Transactional
  public Cliente save(final Cliente cliente) {
    return this.repository.save(cliente);
  }

  public Optional<Cliente> getByCpf(final String cpf) {
    return this.repository.findByCpf(cpf);
  }

}
