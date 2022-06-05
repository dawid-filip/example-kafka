package com.df.service;

import com.df.callback.KafkaCallback;
import com.df.config.KafkaConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@Log4j2
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String message) {
        kafkaTemplate.send(KafkaConfiguration.TOPIC_1, message);
        log.info("Sent message [" + message + "] to [" + KafkaConfiguration.TOPIC_1 + "] topic.");
    }

    @Override
    public void sendMessageWithCallback(String message) {
        kafkaTemplate.send(KafkaConfiguration.TOPIC_2, message)
                .addCallback(new KafkaCallback(message));
    }

    @Override
    public Flux<String> sendRandomMessage(String message) {
        return sendRandomMessageWithCallback(message, (msg) -> this.sendMessage(msg));
    }

    @Override
    public Flux<String> sendRandomMessageWithCallback(String message) {
        return sendRandomMessageWithCallback(message, (msg) -> this.sendMessageWithCallback(msg));
    }

    private Flux<String> sendRandomMessageWithCallback(String message, Consumer<String> sendMessageMethod) {
        return Flux.interval(Duration.ofSeconds(2))
                .map(interval -> {
                    String mergedMessage = new StringBuilder(message)
                            .append("-")
                            .append(UUID.randomUUID().toString())
                            .toString();
                    sendMessageMethod.accept(mergedMessage);
                    return mergedMessage;
                })
                .map(msg -> "Sent message: " + msg + "\n");
    }

}
