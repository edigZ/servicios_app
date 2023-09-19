package com.mx.servicios.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogCC {
  private static final Logger logger = LoggerFactory.getLogger(LogCC.class);

  private LogCC() {
    throw new IllegalStateException("Utility class");
  }

  public static void log(Object message) {
    try {
      message = message.toString().toUpperCase().replace("ERROR", "FALLO");
      logger.info("\"Mensaje\":\"{}\"", message);
    }
    catch (Exception e) {
      logger.debug(e.getMessage());
    }
  }

  public static void logDegug(Object message) {
    logger.debug("\"Mensaje\":\"{}\"", message);
  }
}
