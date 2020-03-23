package cqrs.valueobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.json.JSONArray;

public class ValueObject implements List<ValueRow>, Serializable {
  private static final long serialVersionUID = -5680396943602166024L;
  private List<ValueRow> tbl = new ArrayList<ValueRow>();

  private String voName = "";

  public ValueObject() {
  }

  public ValueObject(String name) {
    this.voName = name;
  }

  public String getName() {
    return this.voName;
  }

  public int size() {
    return this.tbl.size();
  }

  public boolean isEmpty() {
    return this.tbl.isEmpty();
  }

  public boolean contains(Object o) {
    return this.tbl.contains(o);
  }

  public boolean containsKey(String key) {
    return this.containsKey(0, key);
  }

  public boolean containsKey(int index, String key) {
    return this.get(index).containsKey(key);
  }

  public boolean containsValue(Object value) {
    return this.containsValue(0, value);
  }

  public boolean containsValue(int index, Object value) {
    return this.get(index).containsValue(value);
  }

  public Iterator<ValueRow> iterator() {
    return this.tbl.iterator();
  }

  public Object[] toArray() {
    return this.tbl.toArray();
  }

  public <T> T[] toArray(T[] a) {
    return this.tbl.toArray(a);
  }

  public boolean add(ValueRow e) {
    return this.tbl.add(e);
  }

  public boolean remove(Object o) {
    return this.tbl.remove(o);
  }

  public boolean containsAll(Collection<?> c) {
    return this.tbl.containsAll(c);
  }

  public boolean addAll(Collection<? extends ValueRow> c) {
    return this.tbl.addAll(c);
  }

  public boolean addAll(int index, Collection<? extends ValueRow> c) {
    return this.tbl.addAll(c);
  }

  public void addRow(int idx, Map<String, Object> map) {
    addRow(idx, new ValueRow(map));
  }

  public void addRow(Map<String, Object> map) {
    add(new ValueRow(map));
  }

  public boolean removeAll(Collection<?> c) {
    return this.tbl.removeAll(c);
  }

  public boolean retainAll(Collection<?> c) {
    return this.tbl.retainAll(c);
  }

  public void clear() {
    this.tbl.clear();
  }

  public ValueRow get(int index) {
    if (this.size() <= index) {
      throw new IndexOutOfBoundsException("ValueObejct index is out of range");
    }
    return this.get(index);
  }

  public Object get(String key) {
    return this.get(0, key);
  }

  public char getChar(String key) {
    return this.getChar(0, key);
  }

  public boolean getBoolean(String key) {
    return this.getBoolean(0, key);
  }

  public String getString(String key) {
    return this.getString(0, key);
  }

  public int getInt(String key) {
    return this.getInt(0, key);
  }

  public float getFloat(String key) {
    return this.getFloat(0, key);
  }

  public long getLong(String key) {
    return this.getLong(0, key);
  }

  public double getDouble(String key) {
    return this.getDouble(0, key);
  }

  public Object get(String key, Object defaultValue) {
    return this.get(0, key, defaultValue);
  }

  public char getChar(String key, char defaultValue) {
    return this.getChar(0, key, defaultValue);
  }

  public boolean getBoolean(String key, Boolean defaultValue) {
    return this.getBoolean(0, key, defaultValue);
  }

  public String getString(String key, String defaultValue) {
    return this.getString(0, key, defaultValue);
  }

  public int getInt(String key, int defaultValue) {
    return this.getInt(0, key, defaultValue);
  }

  public float getFloat(String key, float defaultValue) {
    return this.getFloat(0, key, defaultValue);
  }

  public long getLong(String key, long defaultValue) {
    return this.getLong(0, key, defaultValue);
  }

  public double getDouble(String key, double defaultValue) {
    return this.getDouble(0, key, defaultValue);
  }

  public Object get(int index, String key, Object defaultValue) {
    return this.getRow(index).get(key, defaultValue);
  }

  public char getChar(int index, String key, char defaultValue) {
    return this.getRow(index).getChar(key, defaultValue);
  }

  public boolean getBoolean(int index, String key, boolean defaultValue) {
    return this.getRow(index).getBoolean(key, defaultValue);
  }

  public String getString(int index, String key, String defaultValue) {
    return this.getRow(index).getString(key, defaultValue);
  }

  public int getInt(int index, String key, int defaultValue) {
    return this.getRow(index).getInt(key, defaultValue);
  }

  public float getFloat(int index, String key, float defaultValue) {
    return this.getRow(index).getFloat(key, defaultValue);
  }

  public long getLong(int index, String key, long defaultValue) {
    return this.getRow(index).getLong(key, defaultValue);
  }

  public double getDouble(int index, String key, double defaultValue) {
    return this.getRow(index).getDouble(key, defaultValue);
  }

  public Object get(int index, String key) {
    return getRow(index).get(key);
  }

  public char getChar(int index, String key) {
    return getRow(index).get(key, ' ');
  }

  public Boolean getBoolean(int index, String key) {
    return getRow(index).getBoolean(key, false);
  }

  public String getString(int index, String key) {
    return getRow(index).getString(key, null);
  }

  public int getInt(int index, String key) {
    return getRow(index).getInt(key, 0);
  }

  public float getFloat(int index, String key) {
    return getRow(index).getFloat(key, 0F);
  }

  public long getLong(int index, String key) {
    return getRow(index).getLong(key, 0L);
  }

  public double getDouble(int index, String key) {
    return getRow(index).getDouble(key, 0D);
  }

  public ValueRow getRow() {
    return this.getRow(0);
  }

  public ValueRow getRow(int index) {
    if (this.size() <= index) {
      throw new IndexOutOfBoundsException("ValueObject index is out of range");
    }
    return tbl.get(index);
  }

  public ValueObject getRowAsVo(int index) {
    ValueObject vo = new ValueObject();
    vo.add(getRow(index));
    return vo;
  }

  public ValueObject getRowAsVo() {
    return getRowAsVo(0);
  }

  public ValueRow set(int index, ValueRow element) {
    return this.set(index, element);
  }

  public void set(String key, Object value) {
    this.set(0, key, value);
  }

  public void set(String key, char value) {
    this.set(0, key, value);
  }

  public void set(String key, Boolean value) {
    this.set(0, key, value);
  }

  public void set(String key, int value) {
    this.set(0, key, value);
  }

  public void set(String key, float value) {
    this.set(0, key, value);
  }

  public void set(String key, long value) {
    this.set(0, key, value);
  }

  public void set(String key, double value) {
    this.set(0, key, value);
  }

  public void set(int idx, String key, Object value) {
    ValueRow row = null;
    if (idx < tbl.size() && tbl.get(idx) != null) {
      row = tbl.get(idx);
    } else {
      row = this.createRowInstance();
      add(idx, row);
    }
    row.put(key, value);
  }

  public void set(int idx, String key, char value) {
    this.set(idx, key, new Character(value));
  }

  public void set(int idx, String key, Boolean value) {
    this.set(idx, key, new Boolean(value));
  }

  public void set(int idx, String key, int value) {
    this.set(idx, key, new Integer(value));
  }

  public void set(int idx, String key, float value) {
    this.set(idx, key, new Float(value));
  }

  public void set(int idx, String key, long value) {
    this.set(idx, key, new Long(value));
  }

  public void set(int idx, String key, double value) {
    this.set(idx, key, new Double(value));
  }

  public void add(int index, ValueRow element) {
    if (size() < index) {
      throw new IndexOutOfBoundsException("ValueObject index is out of range");
    }
    this.tbl.add(index, element);
  }

  public ValueRow remove(int index) {
    return this.tbl.remove(index);
  }

  public int indexOf(Object o) {
    return this.tbl.indexOf(o);
  }

  public int lastIndexOf(Object o) {
    return this.tbl.lastIndexOf(o);
  }

  public ListIterator<ValueRow> listIterator() {
    return this.tbl.listIterator();
  }

  public ListIterator<ValueRow> listIterator(int index) {
    return this.tbl.listIterator(index);
  }

  public List<ValueRow> subList(int fromIndex, int toIndex) {
    return this.tbl.subList(fromIndex, toIndex);
  }

  public void setTable(List<ValueRow> table) {
    this.clear();
    if (table != null) {
      for (int i = 0; i < table.size(); i++) {
        this.add(table.get(i));
      }
    }
  }

  public List<ValueRow> getTable() {
    return tbl;
  }

  private ValueRow createRowInstance() {
    return new ValueRow();
  }

  @Override
  public String toString() {
    JSONArray arr = new JSONArray();
    for (ValueRow row : this.tbl) {
      arr.put(row);
    }

    return arr.toString();
  }
}
