package com.server.cservice.web.security.service;

import com.server.common.domain.model.Card;
import com.server.cservice.web.dto.CardDto;
import com.server.cservice.web.security.SecurityUser;

import java.util.UUID;

public interface SecurityService {
    SecurityUser getUserFromRequest();

    boolean canAccessClient(UUID clientId);

    boolean canAccessCard(UUID cardId);

    boolean canAccessCard(CardDto card);

    boolean canAccessTransaction(UUID transactionId);
}
