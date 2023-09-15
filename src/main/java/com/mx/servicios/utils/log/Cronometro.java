package com.mx.servicios.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class Cronometro {
  private long start;
  private long duration;
  private String msg = null;

  private static final Logger log = LoggerFactory.getLogger(Cronometro.class);

  public Cronometro(boolean arranca, String msg) {
    this.msg = msg;

    if (arranca) {
      this.start = getTimeInMillis();
    }
  }

  public void start() {
    start = getTimeInMillis();

  }

  public void stop() {
    long stop = getTimeInMillis();
    duration = stop - start;
    msg = msg.toUpperCase().replaceAll("ERROR", "FALLO");
    log.info("\"Mensaje\":\"" + msg + "\",\"servicios_App\":[],\"TiempoTotal\":" + duration);

  }

  private long getTimeInMillis() {
    return Calendar.getInstance().getTimeInMillis();
  }

  public long getStart() {
    return start;
  }

  public void setStart(long start) {
    this.start = start;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
