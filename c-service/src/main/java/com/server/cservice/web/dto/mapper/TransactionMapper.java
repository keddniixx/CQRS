package com.server.cservice.web.dto.mapper;

import com.server.common.domain.model.Transaction;
import com.server.cservice.web.dto.TransactionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends Mappable<Transaction, TransactionDto> {
}
