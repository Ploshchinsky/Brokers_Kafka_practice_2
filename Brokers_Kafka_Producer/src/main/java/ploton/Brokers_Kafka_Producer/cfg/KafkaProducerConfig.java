package ploton.Brokers_Kafka_Producer.cfg;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;
import ploton.Brokers_Kafka_Producer.services.KafkaProducer;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaProducerConfig {
    @Bean
    ProducerFactory<String, String> producerFactory() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //Batching Config
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16_384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 5_000);
        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 300);

        //Error Handling
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 300L);
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    KafkaProducer kafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        return new KafkaProducer(kafkaTemplate);
    }
}
