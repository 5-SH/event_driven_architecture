package cqrs.util;

public class StringHelper {
  protected StringHelper() {
  }

  public static boolean isNull(String source) {
    return isNull(source, true);
  }

  public static boolean isNull(String source, boolean isTrim) {
    boolean isNullString = false;
    if (isTrim && source != null) {
      source = source.trim();
    }
    if (source == null || "".equals(source)) {
      isNullString = true;
    }

    return isNullString;
  }

  public static String lPad(String source, int len, char pad) {
    return lPad(source, len, pad, false);
  }

  public static String lPad(String source, int len, char pad, boolean isTrim) {
    if (isTrim) {
      source = source.trim();
    }

    for (int i = source.length(); i < len; i++) {
      source = pad + source;
    }
    return source;
  }

  public static String rPad(String source, int len, char pad) {
    return rPad(source, len, pad, false);
  }

  public static String rPad(String source, int len, char pad, boolean isTrim) {
    if (isTrim) {
      source = source.trim();
    }

    for (int i = source.length(); i < len; i++) {
      source = source + pad;
    }
    return source;
  }

  public static String null2void(String source) {
    if (isNull(source)) {
      source = "";
    }
    return source;
  }

  public static int null2zero(String source) {
    if (isNull(source)) {
      return 0;
    }
    return Integer.parseInt(source);
  }
}
