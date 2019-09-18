package com.gestorinc.security.service.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static com.gestorinc.utils.Constants.SECRET;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

	private String secretKey = SECRET;
	private long validityInMs = 3600000;
}