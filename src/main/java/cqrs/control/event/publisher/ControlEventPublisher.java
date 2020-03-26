package cqrs.control.event.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import cqrs.control.event.data.ControlEventData;
import cqrs.core.kafka.KafkaConfig;
import cqrs.core.publisher.EventPublisher;

// Event 를 외부로 발행
// 일반적으로 Message Broker(Queue) 로 Producing
@Component
public class ControlEventPublisher implements EventPublisher<ControlEventData> {
  public void publish(ControlEventData controlRawEvent) throws Exception {
    // kafkaConfig 생성
    KafkaConfig kafkaConfig = KafkaConfig.getInstance();

    // kafkaProducer 생성
    KafkaProducer<String, String> ControlProducer = new KafkaProducer<String, String>(kafkaConfig.getProperty());

    // controlRawEvent string 으로 직렬화
    ObjectMapper objectMapper = new ObjectMapper();
    String serializedDeviceControlRawEvent = objectMapper.writeValueAsString(controlRawEvent);

    // DEVICE_CONTROL 토픽에 serializedDeviceControlRawEvent 정보를 kafka producer record
    // 에 담아서 변경
    ProducerRecord<String, String> kafkaMessage = new ProducerRecord<String, String>("DEVICE_CONTROL",
        serializedDeviceControlRawEvent);

    // producer.send 직렬화 된 메세지
    ControlProducer.send(kafkaMessage);
    System.out.println("EventPublisher.publish() kafkaProducer sended");
  }
}