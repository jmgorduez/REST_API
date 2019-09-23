package com.gestorinc.repository.entity.enums;

public enum EnumEstadoParticipe {
    A, C, I;

    public boolean isActivo(){
        return A.equals(this);
    }
}