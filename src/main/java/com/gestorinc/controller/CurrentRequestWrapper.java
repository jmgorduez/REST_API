package com.gestorinc.controller;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

import static com.gestorinc.utils.Constants.ANONYMOUS;
import static java.util.Optional.ofNullable;

public class CurrentRequestWrapper extends HttpServletRequestWrapper {

    private final String body;
    private final String ip;
    private final String operation;
    private final String authenticatedBank;

    public CurrentRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        ip = request.getRemoteAddr();
        operation = request.getRequestURI();
        authenticatedBank = authenticatedBank(request);

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        body = stringBuilder.toString();
    }

    private String authenticatedBank(HttpServletRequest request) {

        if (ofNullable(request.getUserPrincipal()).isPresent()) {
            return request.getUserPrincipal().getName();
        }
        return ANONYMOUS;
    }
 
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = getServletInputStream(byteArrayInputStream);
        return servletInputStream;
    }

    private ServletInputStream getServletInputStream(ByteArrayInputStream byteArrayInputStream) {
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }

    public String operationURL() {
        return operation;
    }

    public String authenticatedBank() {

        return authenticatedBank;
    }

    public String clientIpAddress() {
        return ip;
    }
}