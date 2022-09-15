package com.odeyalo.analog.netflix.searchmicroservice.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;

@EnableKafka
@Profile("dev")
public class KafkaConsumerConfiguration {
    private static final String APACHE_KAFKA_MESSAGE_BROKER_CONNECTION_URL = "localhost:9092";
    private static final String APACHE_KAFKA_MESSAGE_BROKER_GROUP_ID_CONFIG = "1";

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory() {
        return new ConcurrentKafkaListenerContainerFactory<>();
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    private HashMap<String, Object> consumerConfig() {
        HashMap<String, Object> config = new HashMap<>(5);
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, APACHE_KAFKA_MESSAGE_BROKER_CONNECTION_URL);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, APACHE_KAFKA_MESSAGE_BROKER_GROUP_ID_CONFIG);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, 10000);
        return config;
    }
}
