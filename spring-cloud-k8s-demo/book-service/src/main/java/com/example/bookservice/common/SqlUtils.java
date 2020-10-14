package com.example.bookservice.common;

/**
 * SqlUtils
 */
public class SqlUtils {
  public static String blurSearchString(final String text) {
    return String.format("%s%s%s", "%", text, "%");
  }
}
