package ploton.Brokers_Kafka_Producer.cfg;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Value("${kafka.topic.partitions}")
    private int partitionAmount;
    @Value("${kafka.topic.replicas}")
    private int replicasAmount;

    @Bean
    NewTopic webTopic() {
        return TopicBuilder.name("web_logs")
                .partitions(partitionAmount)
                .replicas(replicasAmount)
                .build();
    }

    @Bean
    NewTopic dlqTopic() {
        return TopicBuilder.name("dlq")
                .replicas(partitionAmount)
                .replicas(replicasAmount)
                .build();
    }
}
