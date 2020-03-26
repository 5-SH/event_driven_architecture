package cqrs.control.event.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControlEventData extends AbstractEventData<String> {
  String table;

  public ControlEventData() {
    super.date = "";
    super.identifier = "";
    super.payload = "";
    super.seq = 0;
    super.type = "";
    super.version = 0L;
    this.table = "";
  }

  public ControlEventData(String identifier, String type, Long version, String payload) {
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
    this.date = format.format(date);
    this.identifier = identifier;
    this.type = type;
    this.version = version;
    this.payload = payload;
  }

  @Override
  public String getIdentifier() {
    return identifier;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public Long getVersion() {
    return version;
  }

  @Override
  public String getPayload() {
    return payload;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getSeq() {
    return seq;
  }

  public void setSeq(int seq) {
    this.seq = seq;
  }

  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  @Override
  public String toString() {
    return "ControlRawEvent [date=" + date + ", seq=" + seq + ",  identifier=" + identifier + ", type=" + type
        + ", version=" + version + ", payload=" + payload + "]";
  }
}