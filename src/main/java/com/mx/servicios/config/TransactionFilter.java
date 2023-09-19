package com.mx.servicios.config;

import com.mx.servicios.utils.log.LogCC;

import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TransactionFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    LogCC.log("init de filter");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    String ip = IpRequest.getIP(req);
    String url = RemoveSpecial.encode(req.getRequestURI());
    String mensaje;
    mensaje = String.format("%s request enrutado a %s", ip, url);
    LogCC.log(mensaje.toUpperCase().replaceAll("ERROR", "FALLO"));
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    LogCC.log("destroy de filter");
  }

}
