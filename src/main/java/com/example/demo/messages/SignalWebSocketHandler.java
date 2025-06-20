package com.example.demo.messages;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SignalWebSocketHandler implements WebSocketHandler {

    // In-memory room sink mapping
    private final Map<String, Sinks.Many<String>> roomSinks = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String roomId = UriComponentsBuilder.fromUri(session.getHandshakeInfo().getUri())
                .build()
                .getQueryParams()
                .getFirst("room");

        if (roomId == null || roomId.isEmpty()) {
            return session.close();
        }

        log.info("Client connected to room: {}", roomId);

        // Get or create a sink (room message broadcaster)
        Sinks.Many<String> sink = roomSinks.computeIfAbsent(roomId,
                key -> Sinks.many().multicast().onBackpressureBuffer());

        // Receive client messages and broadcast to the room
        Mono<Void> input = session.receive()
                .map(msg -> msg.getPayloadAsText())
                .doOnNext(msg -> {
                    log.info("Received from {}: {}", roomId, msg);
                    sink.tryEmitNext(msg); // Broadcast to all in room
                })
                .then();

        // Send messages from the room sink to this session
        Mono<Void> output = session.send(
                sink.asFlux()
                        .map(session::textMessage));

        return Mono.zip(input, output).then();
    }
}
