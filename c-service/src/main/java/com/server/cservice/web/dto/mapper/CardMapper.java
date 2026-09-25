package com.server.cservice.web.dto.mapper;

import com.server.common.domain.model.Card;
import com.server.cservice.web.dto.CardDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper extends Mappable<Card,CardDto> {
}
