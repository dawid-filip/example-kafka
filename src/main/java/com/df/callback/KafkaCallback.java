package com.df.callback;

import com.df.config.KafkaConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

@AllArgsConstructor
@Log4j2
public class KafkaCallback implements ListenableFutureCallback<SendResult<String, String>> {

    private String message;

    @Override
    public void onSuccess(SendResult<String, String> sendResult) {
        log.info("Sent with callback message [" + message + "] to [" + KafkaConfiguration.TOPIC_2 + "] topic.");
    }

    @Override
    public void onFailure(Throwable throwable) {
        log.warn("Unable to send with callback message [" + message + "] to [" + KafkaConfiguration.TOPIC_2 + "] topic.", throwable);
    }
}
