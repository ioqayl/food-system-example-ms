package org.food.truck.ordering.kafka.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class KafkaMessageHelper {

    public <T> BiConsumer<SendResult<String, T>, Throwable> getKafkaCallback(String responseTopicName,
                                                                             T avroModel,
                                                                             String orderId,
                                                                             String avroModelName) {
        return (result, ex) -> {
            if (ex == null) {
                log.error("Error while sending {}  message {} to topic {}", avroModelName, avroModel.toString(),
                        responseTopicName, ex);
            } else {
                RecordMetadata metadata = result.getRecordMetadata();
                //result.getProducerRecord().key();
                //result.getProducerRecord().value();
                //result.getProducerRecord().partition();
                //result.getProducerRecord().topic();
                //result.getProducerRecord().timestamp();
                //result.getProducerRecord().headers();
                log.info("Received successful response from Kafka for order id: {}" +
                                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        orderId, metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
            }
        };
    }
}
