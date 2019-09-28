package com.gestorinc.controller.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = -6986746375915710855L;
	private String username;
    private String password;
}