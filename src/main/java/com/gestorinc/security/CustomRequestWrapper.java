package com.gestorinc.security;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.gestorinc.utils.Constants.ANONYMOUS;
import static com.gestorinc.utils.Constants.BLANK_SPACE;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

public class CustomRequestWrapper extends HttpServletRequestWrapper {

    private String body;
    private final String ip;
    private final String operation;
    private final String authenticatedBank;

    public CustomRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        ip = request.getRemoteAddr();
        operation = request.getRequestURI();
        authenticatedBank = authenticatedBank(request);

        Optional<BufferedReader> bufferedReader = empty();
        try {
            bufferedReader =
                    ofNullable(new BufferedReader(new InputStreamReader(request.getInputStream())));
            bufferedReader.ifPresent(this::readBodyRequest);
        } finally {
            bufferedReader.ifPresent(this::closeBufferedReader);
        }
    }

    private void readBodyRequest(BufferedReader bufferedReader) {

        body = ofNullable(bufferedReader
                .lines().collect(Collectors.joining()))
                .orElse(BLANK_SPACE);
    }

    private void closeBufferedReader(BufferedReader bufferedReader) {
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            throw new RuntimeException();
        }
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
            //To deploy .jar
            /*@Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }*/

            @Override
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