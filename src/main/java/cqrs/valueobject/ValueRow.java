package cqrs.valueobject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValueRow implements Map<String, Object> {
  private Map<String, Object> row = null;

  public ValueRow() {
    this.row = new HashMap<String, Object>();
  }

  public ValueRow(Map<String, Object> map) {
    this.row = map;
  }

  public ValueRow(ValueRow r) {
    this.row = r.getMap();
  }

  public Map<String, Object> getMap() {
    return this.row;
  }

  public ValueRow clone() {
    return new ValueRow(new HashMap<String, Object>(this.row));
  }

  public int size() {
    return this.row.size();
  }

  public boolean isEmpty() {
    return this.row.isEmpty();
  }

  public boolean containsKey(Object key) {
    return this.row.containsKey(key);
  }

  public boolean containsValue(Object value) {
    return this.row.containsValue(value);
  }

  public Object get(Object key) {
    return this.row.get(key);
  }

  public Object get(String key, Object defaultValue) {
    Object value = this.get(key);

    if (value == null) {
      return defaultValue;
    } else {
      return value;
    }
  }

  public char get(String key, char defaultValue) {
    Object value = this.get(key);

    if (value == null) {
      return defaultValue;
    } else {
      return ((Character) value).charValue();
    }
  }

  public char getChar(String key, char defaultValue) {
    Object value = this.get(key);

    if (value == null) {
      return defaultValue;
    } else {
      return ((Character) value).charValue();
    }
  }

  public boolean getBoolean(String key, boolean defaultValue) {
    Object value = this.get(key);

    if (value == null) {
      return defaultValue;
    } else {
      return ((Boolean) value).booleanValue();
    }
  }

  public String getString(String key, String defaultValue) {
    Object value = this.get(key);

    if (value == null) {
      return defaultValue;
    } else {
      return value.toString();
    }
  }

  public int getInt(String key, int defaultValue) {
    Object value = this.get(key);

    if (value == null) {
      return defaultValue;
    } else {
      return ((Number) value).intValue();
    }
  }

  public float getFloat(String key, float defaultValue) {
    Object value = this.get(key);

    if (value == null) {
      return defaultValue;
    } else {
      return ((Number) value).floatValue();
    }
  }

  public long getLong(String key, long defaultValue) {
    Object value = this.get(key);

    if (value == null) {
      return defaultValue;
    } else {
      return ((Number) value).longValue();
    }
  }

  public double getDouble(String key, double defaultValue) {
    Object value = this.get(key);

    if (value == null) {
      return defaultValue;
    } else {
      return ((Number) value).doubleValue();
    }
  }

  public Object put(String key, Object value) {
    return this.row.put(key, value);
  }

  public Object remove(Object key) {
    return this.row.remove(key);
  }

  public void putAll(Map<? extends String, ? extends Object> m) {
    this.row.putAll(m);
  }

  public void clear() {
    this.row.clear();
  }

  public Set<String> keySet() {
    return this.row.keySet();
  }

  public Collection<Object> values() {
    return this.row.values();
  }

  public Set<Entry<String, Object>> entrySet() {
    return this.row.entrySet();
  }

}
