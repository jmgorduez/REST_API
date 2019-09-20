package com.gestorinc.utils;

import com.gestorinc.repository.entity.Persona;
import com.gestorinc.repository.entity.PersonaPK;
import com.gestorinc.repository.entity.Producto;
import com.gestorinc.repository.entity.ProductoPK;

import java.math.BigDecimal;

public class DataTestGenerator {

    public static final String _11111111111111111111111111111111111 = "11111111111111111111111111111111111";
    public static final String JUAN_MANUEL_GARCIA = "Juan Manuel Garcia";
    public static final String FONDO_APV01_APV0000000001 = "Fondo APV01 APV0000000001";
    public static final String FONDO_APV01 = "Fondo APV01";
    public static final String APV0000000001 = "APV0000000001";
    public static final String APV01 = "APV01";
    public static final BigDecimal _1000 = new BigDecimal(1000);
    public static final Long _1234 = new Long(1234);
    public static final Integer NUM_LICENCIA = 1;
    public static final String COD_EMPRESA = "1";
    public static final Persona PERSONA_1234 = new Persona(new PersonaPK(NUM_LICENCIA, _1234), JUAN_MANUEL_GARCIA);
    public static final Producto PRODUCTO_APV01 = new Producto(new ProductoPK(NUM_LICENCIA,COD_EMPRESA, APV01), FONDO_APV01);
}
