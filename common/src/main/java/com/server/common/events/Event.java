package com.server.common.events;


import com.server.common.domain.aggregate.Aggregate;

public interface Event {
    void apply(Aggregate aggregate);
}
