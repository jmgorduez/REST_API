package com.gestorinc.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

import static com.gestorinc.utils.Constants.CÓDIGO_DE_BANCO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserinfoResponse implements Serializable {

	private static final long serialVersionUID = -6986746375915710855L;
	@ApiModelProperty(notes = CÓDIGO_DE_BANCO, required = true, position = 1)
	private String username;
}