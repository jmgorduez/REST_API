package com.gestorinc.repository.entity.enums;

public enum EnumEstadoIntencionAporte {
    PEN, VEN, NTF, RES, PAG;

    public boolean esPagado(){
        return PAG.equals(this);
    }

    public boolean esVencido(){
        return VEN.equals(this);
    }

    public boolean esNotificado(){
        return NTF.equals(this);
    }
}