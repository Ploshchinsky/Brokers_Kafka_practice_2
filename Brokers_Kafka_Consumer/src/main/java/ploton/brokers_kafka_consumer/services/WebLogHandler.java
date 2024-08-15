package ploton.brokers_kafka_consumer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebLogHandler {

    @KafkaListener(topics = "web_logs", groupId = "weblog_group")
    public void listen(ConsumerRecord<String, String> message) {
        log.debug("WebLogHandler: Success receive message from Kafka topic - "
                + message.value() + " [" + message.key() + "]");
    }
}
