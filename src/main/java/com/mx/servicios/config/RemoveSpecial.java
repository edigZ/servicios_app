package com.mx.servicios.config;

public class RemoveSpecial {
  private RemoveSpecial() {
    throw new IllegalStateException("Utility class");
  }

  public static String validaSplitting(String str) {
    if (str == null) {
      return "";
    }
    str = str.replace('\n', '_').replace('\r', '_').replace('\t', '_');

    return str;
  }

  public static String encode(String message) {
    if (message == null) {
      return "";
    }
    message = message.replace('\n', '_').replace('\r', '_').replace('\t', '_');

    return message;
  }
}
