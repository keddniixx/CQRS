package com.server.common.service.client;


import com.server.common.domain.model.Client;
import com.server.common.service.QueryService;

import java.util.UUID;

public interface ClientQueryService
        extends QueryService<Client> {

    boolean existsByUsername(String username);

    Client getByUsername(String username);

    Client getByAccount(UUID accountId);
}
