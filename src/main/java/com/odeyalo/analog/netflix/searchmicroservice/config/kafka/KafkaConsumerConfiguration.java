package com.odeyalo.analog.netflix.searchmicroservice.config.kafka;

import com.odeyalo.support.clients.filestorage.dto.ImageSuccessfulUploadedResponseDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;

@Configuration
public class KafkaConsumerConfiguration {
    private static final String APACHE_KAFKA_MESSAGE_BROKER_CONNECTION_URL = "localhost:9092";
    private static final String APACHE_KAFKA_MESSAGE_BROKER_GROUP_ID_CONFIG = "1";

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ImageSuccessfulUploadedResponseDTO> concurrentKafkaListenerContainerFactory(ConsumerFactory<String, ImageSuccessfulUploadedResponseDTO> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, ImageSuccessfulUploadedResponseDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ImageSuccessfulUploadedResponseDTO> imageSuccessfulUploadedResponseDTOConsumerFactory() {
        JsonDeserializer<ImageSuccessfulUploadedResponseDTO> jsonDeserializer = new JsonDeserializer<>(ImageSuccessfulUploadedResponseDTO.class);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public HashMap<String, Object> consumerConfig() {
        HashMap<String, Object> config = new HashMap<>(5);
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, APACHE_KAFKA_MESSAGE_BROKER_CONNECTION_URL);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, APACHE_KAFKA_MESSAGE_BROKER_GROUP_ID_CONFIG);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, 10000);
        return config;
    }
}
