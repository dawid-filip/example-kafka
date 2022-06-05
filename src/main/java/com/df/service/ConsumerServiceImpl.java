package com.df.service;

import com.df.config.KafkaConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ConsumerServiceImpl implements ConsumerService {

    @KafkaListener(topics = KafkaConfiguration.TOPIC_1, groupId = KafkaConfiguration.GROUP_ID_1)
    @Override
    public void retrieveMessage(String message) {
        log.info("Retrieved message {" + message + "} from {" + KafkaConfiguration.TOPIC_1 + "} topic.");
    }

    @KafkaListener(topics = KafkaConfiguration.TOPIC_2, groupId = KafkaConfiguration.GROUP_ID_1)
    @Override
    public void retrieveMessageWithCallback(String message) {
        log.info("Retrieved with callback message {" + message + "} from {" + KafkaConfiguration.TOPIC_1 + "} topic.");
    }

}
