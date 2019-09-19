package com.gestorinc.utils;

public class Constants {

    public static final String V1_CONSULTAR_CLIENTE = "/v1/consultarCliente";
    public static final String V1_NOTIFICAR_APORTE = "/v1/notificarAporte";
    public static final String V1_CONFIRMAR_APORTE = "/v1/confirmarAporte";
    public static final String AUTH_SIGNIN = "/auth/signin";
    public static final String ME = "/me";

    public static final String JWT_AUTHENTICATION_FAILED = "Jwt authentication failed";
    public static final String INVALID_USERNAME_PASSWORD_SUPPLIED = "Invalid username/password supplied";
    public static final String EXPIRED_OR_INVALID_JWT_TOKEN = "Expired or invalid JWT token";
    public static final String SECRET = "secret";
    public static final String $_SECURITY_JWT_TOKEN_SECRET_KEY_SECRET = "${security.jwt.token.secret-key:secret}";
    public static final String $_SECURITY_JWT_TOKEN_EXPIRE_LENGTH_3600000 = "${security.jwt.token.expire-length:3600000}";
    public static final String AUTHORIZATION = "Authorization";
    public static final String USERNAME = "username";
    public static final String TOKEN = "token";
    public static final String OK = "OK";
    public static final String ER = "ER";
    public static final String EXCEPTION_ = "Exception: ";
    public static final String APPLICATION_JSON = "application/json";
}
