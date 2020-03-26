package cqrs.core.kafka;

import java.util.Properties;

/**
 * properties 는 두 번 생성될 필요 없음 -> properties 리턴하는 클래스를 on demand hlder idiom 으로
 * 개발 kafka 의 설정은 생성해서 만들어야 한다는 걸 강조하기 위해 new 로 생성할 수 있도록 bean 으로 만들지 않는다.
 * property 를 인자로 가지고 getter 로 가져온다.
 **/
public class KafkaConfig {
  private Properties properties;

  private KafkaConfig() {
    properties = new Properties();

    // kafka host 및 server 설정
    properties.put("bootstrap.servers", "localhost:9092");
    // 자신이 보낸 메세지에 대해 kafka 로 부터 확인을 기다리지 않음
    properties.put("acks", "all");
    // 서버로 보낼 레코드를 버퍼링 할 때 사용할 수 있는 전체 메모리 바이트 수
    properties.put("block.on.buffer.full", "true");
    // serialize 설정
    properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    properties.put("value.serializer", "org.apache.kafka.common.serialization.Stringserializer");
  }

  private static class Singleton {
    private static final KafkaConfig instance = new KafkaConfig();
  }

  public static KafkaConfig getInstance() {
    return Singleton.instance;
  }

  public Properties getProperty() {
    // sngleton 확인
    System.out.println("KafkaConfig.getConfig() class : " + properties.toString());

    return this.properties;
  }
}