package com.gestorinc.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

import static com.gestorinc.utils.Constants.*;

@Getter
public class AuthenticationResponse extends AbstractRestControllerResponse implements Serializable {

	private static final long serialVersionUID = -6986746375915710855L;

	@ApiModelProperty(notes = CÓDIGO_DE_BANCO, allowEmptyValue = false,
	position = 1)
	private String username;
	@ApiModelProperty(notes = ACCESS_TOKEN, allowEmptyValue = false,
	position = 2)
    private String access_token;
	@ApiModelProperty(notes = TIPO_DE_TOKEN, allowEmptyValue = false,
			position = 3)
	private String token_type;
	@ApiModelProperty(notes = TIEMPO_DE_VALIDEZ_DEL_TOKEN, allowEmptyValue = false,
			position = 4)
	private Integer expires_in;

	@Builder
	public AuthenticationResponse(@JsonProperty("respuesta") String respuesta,
								  @JsonProperty("username") String username,
								  @JsonProperty("access_token") String access_token,
								  @JsonProperty("token_type") String token_type,
								  @JsonProperty("expires_in") Integer expires_in) {
		super(respuesta);
		this.username = username;
		this.access_token = access_token;
		this.token_type = token_type;
		this.expires_in = expires_in;
	}
}