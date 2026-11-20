package com.server.cservice.web.security;

import com.server.common.domain.exception.RNF;
import com.server.common.domain.model.Client;
import com.server.cservice.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    private final ClientService clientService;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        Client client = null;
        int attempts = 0;
        while (attempts < 15) {
            try {
                client = clientService.getByUsername(username);
                if (client != null) {
                    break;
                }
            } catch (RNF e) {
            }
            attempts++;
            Thread.sleep(500);
        }
        if (client == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new SecurityUser(client);
    }
}
