package com.server.cservice.service.client;


import com.server.common.service.QueryService;
import com.server.cservice.service.CommandSerivice;
import com.server.common.domain.model.Client;

public interface ClientService
        extends QueryService<Client>, CommandSerivice<Client> {

    boolean existsByUsername(String username);

    Client getByUsername(String username);
}
