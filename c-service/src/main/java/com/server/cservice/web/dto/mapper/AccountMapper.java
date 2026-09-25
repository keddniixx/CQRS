package com.server.cservice.web.dto.mapper;


import com.server.common.domain.model.Account;
import com.server.cservice.web.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper extends Mappable<Account, AccountDto> {
}
