package com.server.common.service.client;


import com.server.common.domain.exception.RNF;
import com.server.common.domain.model.Client;
import com.server.common.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ClientQueryServiceImpl implements ClientQueryService {

    private final ClientRepository repository;

    @Override
    public Client getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(RNF::new);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Client getByUsername(String username) {
        return repository.getByUsername(username)
                .orElseThrow(RNF::new);
    }

    /**
     * @param id
     * @return {@link Client}
     */
    @Override
    public Client getByAccount(
            final UUID id
    ) {
        return repository.findByAccountId(id)
                .orElseThrow(RNF::new);
    }
}
