package com.gestorinc.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

import static com.gestorinc.utils.Constants.CÓDIGO_DE_BANCO;
import static com.gestorinc.utils.Constants.TOKEN;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = -6986746375915710855L;
	@ApiModelProperty(notes = CÓDIGO_DE_BANCO, allowEmptyValue = false,
	position = 1)
	private String username;
	@ApiModelProperty(notes = TOKEN, allowEmptyValue = false,
	position = 2)
    private String token;
}