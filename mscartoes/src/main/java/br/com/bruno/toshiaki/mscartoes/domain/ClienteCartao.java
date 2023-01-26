package br.com.bruno.toshiaki.mscartoes.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class ClienteCartao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String cpf;
  @ManyToOne
  @JoinColumn(name = "id_cartao")
  private Cartao cartao;

  private BigDecimal limite;


  public static ClienteCartao criarClienteCartao(final Cartao cartao, final String cpf, final BigDecimal limite) {
    final var clienteCartao = new ClienteCartao();
    clienteCartao.setCartao(cartao);
    clienteCartao.setCpf(cpf);
    clienteCartao.setLimite(limite);

    return clienteCartao;
  }


}
