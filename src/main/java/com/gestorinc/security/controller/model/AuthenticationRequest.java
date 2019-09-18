package com.gestorinc.security.controller.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = -6986746375915710855L;
	@Getter
	private String username;
	@Getter
    private String password;
}