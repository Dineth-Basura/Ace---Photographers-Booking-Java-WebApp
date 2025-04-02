package com.example.utils;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encoding = "UTF-8";
    private boolean forceEncoding = true;

    public void init(FilterConfig config) {
        String encodingParam = config.getInitParameter("encoding");
        String forceEncodingParam = config.getInitParameter("forceEncoding");

        if (encodingParam != null) {
            encoding = encodingParam;
        }
        if (forceEncodingParam != null) {
            forceEncoding = Boolean.parseBoolean(forceEncodingParam);
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (forceEncoding || request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(encoding);
        }
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    public void destroy() {}
}