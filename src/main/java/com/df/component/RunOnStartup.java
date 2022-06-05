package com.df.component;

import com.df.service.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RunOnStartup {

    private final ProducerService producerService;

    @EventListener(ContextRefreshedEvent.class)
    public void runOnStartUp() {
        producerService.sendMessage("myTestMessage");
        producerService.sendMessageWithCallback("myTestMessageAsync");
    }

}
