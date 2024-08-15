package ploton.Brokers_Kafka_Producer.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebLogProducer {
    private final KafkaProducer kafkaProducer;

    @PostConstruct
    public void fakeProcessing() {
        List<String> fakeLogs = IntStream.range(0, 1_000)
                .mapToObj(num -> "Log " + num)
                .toList();

        log.debug("WebLogProducer: Start sending logs...");
        fakeLogs.forEach(string -> {
            try {
                Thread.sleep(5_00);
            } catch (InterruptedException e) {
                log.warn("WebLogProducer: Thread is interrupted! - " + e.getMessage());
                Thread.currentThread().interrupt();
            }

            String key = "K" + string.chars().reduce(Integer::sum).getAsInt() % 4;
            log.debug("WebLogProducer: Key - " + key);
            kafkaProducer.sendMessage("web_logs", key, string);
            log.debug("WebLogProducer: Log has been sent to Kafka! - " + string);
        });
    }
}
