package com.server.cservice.web.dto.mapper;

import com.server.common.domain.model.Client;
import com.server.cservice.web.dto.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends Mappable<Client, ClientDto>{
}
