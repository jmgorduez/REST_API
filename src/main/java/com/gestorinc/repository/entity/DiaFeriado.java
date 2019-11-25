package com.gestorinc.repository.entity;

import com.gestorinc.repository.entity.enums.EnumSiNo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ADM_DIA_FERIADO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiaFeriado implements Serializable {

    private static final long serialVersionUID = 7597916494789113677L;
    @Id
    @Column(name = "ID", nullable = false)
    protected Integer id;
    @Column(name = "DIA", nullable = false)
    protected Integer dia;
    @Column(name = "MES", nullable = false)
    protected Integer mes;
}