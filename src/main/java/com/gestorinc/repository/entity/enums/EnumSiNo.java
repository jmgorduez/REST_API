package com.gestorinc.repository.entity.enums;

public enum EnumSiNo {

    S, N;

    public Boolean getVerdadero() {
        return S.equals(this);
    }

    public Boolean getFalso() {
        return N.equals(this);
    }
}