package com.example.springgraphqldemo.utils;

import java.util.Arrays;

/**
 * ObjectUtils
 */
public class ObjectUtils {
  public static String[] filterNullFields (final Object object) {
    return Arrays.asList(object.getClass().getDeclaredFields()).stream().filter(field -> {
      try {
        field.setAccessible(true);
        if (field.get(object) == null) {
          return true;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      return false;
    }).map(field -> field.getName())
    .toArray(String[]::new);
  }
}
