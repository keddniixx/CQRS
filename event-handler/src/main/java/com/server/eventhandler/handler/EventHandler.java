package com.server.eventhandler.handler;

import org.springframework.kafka.support.Acknowledgment;
import com.google.gson.JsonObject;

public interface EventHandler {

    void handle(
            JsonObject object,
            Acknowledgment acknowledgment
    );

}
