package com.server.cservice.repository;

import com.server.cservice.domain.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    Optional<Card> findByNumberAndDateAndCvv(String number, String date, String cvv);
    Optional<Card> findByNumberAndDate(String number, String date);
}
