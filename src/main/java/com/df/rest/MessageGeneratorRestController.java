package com.df.rest;

import com.df.service.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/kafka")
@AllArgsConstructor
public class MessageGeneratorRestController {

    private final ProducerService producerService;

    // http://localhost:8080/api/v1/kafka/generate-message
    @GetMapping(value = {"/generate-message", "/"}, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Flux<String>> sendRandomMessage() {
        return ResponseEntity.status(HttpStatus.OK)
                .allow(HttpMethod.GET)
                .body(producerService.sendRandomMessage("testRandomMessage"));
    }

    // http://localhost:8080/api/v1/kafka/generate-message-with-callback
    @GetMapping(value = "/generate-message-with-callback", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Flux<String>> sendRandomMessageWithCallback() {
        return ResponseEntity.status(HttpStatus.OK)
                .allow(HttpMethod.GET)
                .body(producerService.sendRandomMessageWithCallback("testRandomMessageWithCallback"));
    }

}
