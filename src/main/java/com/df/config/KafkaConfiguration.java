package com.df.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;

import java.util.Map;

import static java.util.Map.entry;

@Configuration
@EnableKafka
public class KafkaConfiguration {

    private static final String SERVER_URL = "localhost:9092";

    public static final String TOPIC_1 = "test-topic-1";
    public static final String TOPIC_2 = "test-topic-2";

    public final static String GROUP_ID_1 = "test-group-id-1";

    // --- ADMIN --- //
    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(
                Map.ofEntries(
                        entry(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER_URL)
                )
        );
    }

    @Bean
    public NewTopic myTopic1() {
        return TopicBuilder.name(TOPIC_1)
                .partitions(2)
                .replicas(3)
                .build();
    }

    @Bean
    public NewTopic myTopic2() {
        return TopicBuilder.name(TOPIC_2)
                .partitions(2)
                .replicas(3)
                .build();
    }

    // --- PRODUCER --- //
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory(
                Map.ofEntries(
                        entry(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER_URL),
                        entry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class),
                        entry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
                )
        );
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // --- CONSUMER --- //
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory(
                Map.ofEntries(
                        entry(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER_URL),
                        entry(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_1),
                        entry(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class),
                        entry(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                )
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
