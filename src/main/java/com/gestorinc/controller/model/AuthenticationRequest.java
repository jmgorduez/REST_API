package com.gestorinc.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

import static com.gestorinc.utils.Constants.CONTRASEÑA_DE_ACCESO;
import static com.gestorinc.utils.Constants.CÓDIGO_DE_BANCO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = -6986746375915710855L;

	@ApiModelProperty(notes = CÓDIGO_DE_BANCO, required = true)
	private String username;
	@ApiModelProperty(notes = CONTRASEÑA_DE_ACCESO, required = true)
    private String password;
}