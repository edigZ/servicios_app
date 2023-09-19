package com.mx.servicios.utils.response;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mx.servicios.utils.constantes.Generales;

public class Folio {
  public static String getFolio() {

    LocalDateTime localDateTime = LocalDateTime.now();

    Pattern p = Pattern.compile("[-:T.]");
    Matcher m = p.matcher(localDateTime.toString());
    String remplazo = "";
    if (m.find()) {
      remplazo = m.replaceAll("");
    }
    if (remplazo.length() > 16) {
      remplazo = remplazo.substring(0, 16);
    }
    return Generales.CODIGO_APP.concat("-").concat(remplazo).concat("00");
  }

  private Folio() {
    throw new IllegalStateException("Utility class");
  }
}
