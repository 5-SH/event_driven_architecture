package cqrs.valueobject;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

public class ValueObjectAssembler implements Map<String, ValueObject>, Serializable {
  private static final long serialVersionUID = 7613765125698297802L;
  private Map<String, ValueObject> vos = new HashMap<String, ValueObject>();

  public ValueObjectAssembler() {
  }

  public int size() {
    return this.vos.size();
  }

  public boolean isEmpty() {
    return this.vos.isEmpty();
  }

  public boolean containsKey(Object key) {
    return this.vos.containsKey(key);
  }

  public boolean containsValue(Object value) {
    return this.vos.containsValue(value);
  }

  public ValueObject get(Object key) {
    return this.vos.get(key);
  }

  public ValueObject put(String key, ValueObject value) {
    return this.vos.put(key, value);
  }

  public ValueObject remove(Object key) {
    return this.vos.remove(key);
  }

  public void putAll(Map<? extends String, ? extends ValueObject> m) {
    this.vos.putAll(m);
  }

  public void clear() {
    this.vos.clear();
  }

  public Set<String> keySet() {
    return this.vos.keySet();
  }

  public Collection<ValueObject> values() {
    return this.vos.values();
  }

  public Set<Entry<String, ValueObject>> entrySet() {
    return this.vos.entrySet();
  }

  @Override
  public String toString() {
    JSONObject obj = new JSONObject(this.vos);
    return obj.toString();
  }

}
