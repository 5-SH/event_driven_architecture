package cqrs.util.json;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cqrs.util.StringHelper;
import cqrs.valueobject.ValueObject;
import cqrs.valueobject.ValueObjectAssembler;
import cqrs.valueobject.ValueRow;

public class JsonHelper {

  public static ValueObjectAssembler parseJson2ValueObjectAssembler(String message) throws JSONException {

    if (StringHelper.isNull(message)) {

      return null;
    }

    JSONObject json = new JSONObject(message);
    Iterator<String> keys = json.keys();
    String key = null;

    ValueObjectAssembler retVoa = new ValueObjectAssembler();

    while (keys.hasNext()) {
      key = keys.next();
      Object child = json.get(key);
      retVoa.put(key, parseJson2ValueObject(child));
    }

    return retVoa;
  }

  public static ValueObject parseJson2ValueObject(String message) throws JSONException {

    JSONObject root = new JSONObject(message);
    String rootKey = root.keys().next();

    JSONObject json = root.getJSONObject(rootKey);
    Iterator<String> keys = json.keys();

    ValueObject retVo = new ValueObject();
    String key = null;
    while (keys.hasNext()) {
      key = keys.next();
      retVo.set(key, json.get(key));
    }

    return retVo;

  }

  public static String parseValueObjectAssembler2Json(ValueObjectAssembler voa) {
    if (voa == null || voa.size() == 0) {
      return "";
    }

    Iterator<String> keys = voa.keySet().iterator();
    String key = null;

    JSONObject json = new JSONObject();
    while (keys.hasNext()) {
      key = keys.next();
      ValueObject vo = voa.get(key);
      if (vo != null) {
        json.put(key, parseValueObject2JsonArray(vo));
      }
    }

    return json.toString(2);
  }

  public static String parseValueObject2Json(ValueObject vo) {
    JSONObject json = new JSONObject();

    if (vo.size() > 0) {
      ValueRow row = vo.get(0);
      Iterator<String> keys = row.keySet().iterator();
      String key = null;
      while (keys.hasNext()) {
        key = keys.next();
        json.put(key, row.get(key));
      }
    }

    return json.toString(2);
  }

  public static ValueObject parseJson2ValueObject(Object json) {
    ValueObject retVo = null;
    if (json.getClass().equals(JSONArray.class)) {
      retVo = parseJson2ValueObject((JSONArray) json);
    } else if (json.getClass().equals(JSONObject.class)) {
      retVo = parseJson2ValueObject((JSONObject) json);
    }
    return retVo;
  }

  public static ValueObject parseJson2ValueObject(JSONArray jsons) {
    ValueObject retVo = new ValueObject();
    for (int i = 0; i < jsons.length(); i++) {
      retVo.add(parseJson2ValueRow((JSONObject) jsons.get(i)));
    }
    return retVo;
  }

  public static ValueObject parseJson2ValueObject(JSONObject json) {
    ValueObject retVo = new ValueObject();
    retVo.add(parseJson2ValueRow(json));
    return retVo;
  }

  public static ValueRow parseJson2ValueRow(JSONObject json) {
    String key = null;
    ValueRow row = new ValueRow();

    Iterator<String> keys = json.keys();
    while (keys.hasNext()) {
      key = keys.next();
      row.put(key, json.get(key));
    }

    return row;
  }

  public static JSONArray parseValueObject2JsonArray(ValueObject vo) {
    JSONArray arr = new JSONArray();

    for (int i = 0; i < vo.size(); i++) {
      arr.put(vo.getRow(i));
    }

    return arr;
  }

  public static String getMessageFromVoa(ValueObjectAssembler pVoa, boolean issingle) {
    Iterator<String> keys = pVoa.keySet().iterator();
    JSONObject message = new JSONObject();
    while (keys.hasNext()) {
      String key = keys.next();
      ValueObject vo = pVoa.get(key);
      message.put(key, vo.getRow(0));
    }

    return message.toString();
  }
}
