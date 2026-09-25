package com.server.cservice.service.event;


import com.server.common.events.AbstractEvent;

public interface EventService {
    void create(AbstractEvent event);
}
