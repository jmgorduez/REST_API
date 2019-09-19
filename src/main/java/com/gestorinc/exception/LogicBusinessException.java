package com.gestorinc.exception;

import com.gestorinc.exception.enums.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class LogicBusinessException extends RuntimeException {
    @Getter
    private Error error;
}
