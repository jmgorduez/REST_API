package com.gestorinc.exception;

import com.gestorinc.exception.enums.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LogicBusinessException extends RuntimeException {

    private Error error;
}
