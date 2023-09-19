package com.mx.servicios.config;

import jakarta.servlet.http.HttpServletRequest;

public class IpRequest {
  private IpRequest() {
    throw new IllegalStateException("Utility class");
  }

  public static String getIP(HttpServletRequest request) {
    String ip = "";
    if (request != null) {
      ip = RemoveSpecial.encode(request.getHeader("X-FORWARDED-FOR"));

      if (ip == null || ("").equals(ip)) {
        ip = RemoveSpecial.encode(request.getRemoteAddr());
      }
    }
    return ip;
  }
}
