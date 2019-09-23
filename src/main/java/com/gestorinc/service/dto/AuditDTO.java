package com.gestorinc.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AuditDTO {

    public enum Status{ OK, ER};

    private String user;
    private String operation;
    private String product;
    private String participant;
    private String ip;
    private String jsonRequestFrame;
    private String jsonResponseFrame;
    private Status status;
    private String message;
}
