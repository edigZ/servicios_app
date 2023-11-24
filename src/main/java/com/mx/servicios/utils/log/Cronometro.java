package com.mx.servicios.utils.log;

import java.util.Calendar;

public class Cronometro {
  private long start;
  private long duration;
  private String msg;

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
    msg = msg.toUpperCase().replace("ERROR", "FALLO");
    LogCC.log(msg + ", servicios_App," + " Tiempo-Total:" + duration);
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
