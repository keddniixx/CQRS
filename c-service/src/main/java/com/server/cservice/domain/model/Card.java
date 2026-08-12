package com.server.cservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    private String number;
    private String date;
    private String cvv;

    @ManyToOne
    @JsonIgnore
    private Account account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "cards_transactions",
        joinColumns = @JoinColumn(name = "card_id"),
        inverseJoinColumns = @JoinColumn(name = "transaction_id")
    )
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList<>();

    public Card() {}

    public Card(Account account) {
        this.account = account;
    }
}
