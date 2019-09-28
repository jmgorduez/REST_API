package com.gestorinc.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SavingFundAccountDTO {

    private String accountDescription;
    private String accountNumber;
    private String gLNCode;
}
