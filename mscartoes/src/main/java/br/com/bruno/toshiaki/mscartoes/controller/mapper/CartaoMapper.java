package br.com.bruno.toshiaki.mscartoes.controller.mapper;

import br.com.bruno.toshiaki.mscartoes.controller.request.CartaoSaveRequest;
import br.com.bruno.toshiaki.mscartoes.domain.Cartao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartaoMapper {

  CartaoMapper INSTANCE = Mappers.getMapper(CartaoMapper.class);

  @Mapping(target = "id", ignore = true)
  Cartao toEntity(final CartaoSaveRequest request);
}
