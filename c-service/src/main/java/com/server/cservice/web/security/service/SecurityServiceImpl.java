package com.server.cservice.web.security.service;

import com.server.common.domain.model.Card;
import com.server.common.domain.model.Client;
import com.server.common.domain.model.Transaction;
import com.server.cservice.service.card.CardService;
import com.server.cservice.service.client.ClientService;
import com.server.cservice.service.transaction.TransactionService;
import com.server.cservice.web.dto.CardDto;
import com.server.cservice.web.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("ssi")
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {


    private final ClientService clientService;
    private final CardService cardService;
    private final TransactionService transactionService;

    @Override
    public SecurityUser getUserFromRequest() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()){
            return null;
        }
        if (authentication.getPrincipal().equals("anonymousUser")){
            return null;
        }
        return (SecurityUser) authentication.getPrincipal();
    }

    @Override
    public boolean canAccessClient(UUID clientId) {
        SecurityUser user = getUserFromRequest();
        UUID id = user.getId();
        return clientId.equals(id);
    }

    @Override
    public boolean canAccessCard(UUID cardId) {
        SecurityUser user = getUserFromRequest();
        UUID id = user.getId();
        Client client = clientService.getById(id);
        return client.getCards().stream()
                .anyMatch(card -> card.getId().equals(cardId));
    }

    @Override
    public boolean canAccessCard(CardDto card) {
        try{
            Card fouundCard = cardService.getByNumberAndDateAndCvv(
                    card.getNumber(),
                    card.getDate(),
                    card.getCvv()
            );
            return canAccessCard(fouundCard.getId());
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean canAccessTransaction(UUID transactionId) {
        SecurityUser user = getUserFromRequest();
        UUID id = user.getId();
        Client client = clientService.getById(id);
        Transaction transaction = transactionService.getById(transactionId);
        return client.getCards().contains(transaction.getFrom())
                || client.getCards().contains(transaction.getTo());
    }
}
