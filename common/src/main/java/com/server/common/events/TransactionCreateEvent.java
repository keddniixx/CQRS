package com.server.common.events;

import com.server.common.domain.aggregate.Aggregate;
import com.server.common.domain.model.Transaction;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TransactionCreateEvent extends AbstractEvent {


    public TransactionCreateEvent(Transaction payload){
        super(null,EventType.TRANSACTION_CREATE,payload);
    }

    @Override
    public void apply(Aggregate aggregate) {

    }
}
