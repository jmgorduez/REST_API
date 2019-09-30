package com.gestorinc.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

import static com.gestorinc.utils.Constants.CÓDIGO_DE_BANCO;

@Getter
public class UserinfoResponse extends AbstractRestControllerResponse implements Serializable {

	private static final long serialVersionUID = -6986746375915710855L;
	@ApiModelProperty(notes = CÓDIGO_DE_BANCO, required = true, position = 1)
	private String username;

	@Builder
	public UserinfoResponse(String respuesta, String username) {
		super(respuesta);
		this.username = username;
	}
}