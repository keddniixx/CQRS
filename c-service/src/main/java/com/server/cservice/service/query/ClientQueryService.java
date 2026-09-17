package com.server.cservice.service.query;

import com.server.cservice.domain.model.Client;
import com.server.cservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientQueryService {
    private final ClientRepository repository;
    
    public Client getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public Client getByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
