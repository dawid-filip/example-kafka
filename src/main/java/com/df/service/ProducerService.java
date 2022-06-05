package com.df.service;

import reactor.core.publisher.Flux;

public interface ProducerService {
    void sendMessage(String message);
    void sendMessageWithCallback(String message);

    Flux<String> sendRandomMessage(String message);
    Flux<String> sendRandomMessageWithCallback(String message);
}
