package br.com.bruno.toshiaki.clientmicroserver.controller.mapper;

import br.com.bruno.toshiaki.clientmicroserver.controller.request.ClienteSaveRequest;
import br.com.bruno.toshiaki.clientmicroserver.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

  ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

  @Mapping(target = "id", ignore = true)
  Cliente toEntity(final ClienteSaveRequest request);

}
