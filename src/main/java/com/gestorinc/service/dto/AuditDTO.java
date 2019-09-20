package com.gestorinc.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuditDTO {
    private String user;
    private String operation;
    private String product;
    private String participant;
    private String ip;
    private String jsonRequestFrame;
    private String jsonResponseFrame;
}
