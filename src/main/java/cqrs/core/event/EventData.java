package cqrs.core.event;

// Projector 에서 DB 에 보내는 데이터 객체, Publisher 에서 kafka 로 보내는 데이터 객체
public interface EventData<ID> {
  ID getIdentifier();

  String getType();

  Long getVersion();

  String getPayload();
}