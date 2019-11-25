package com.gestorinc.repository;

import com.gestorinc.exception.LogicBusinessException;
import com.gestorinc.repository.entity.DiaFeriado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static com.gestorinc.exception.enums.Error.FECHA_DIA_FERIADO_24;
import static com.gestorinc.exception.enums.Error.PRODUCTO_CON_GLN_NO_ENCONTRADO;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;

@Repository
public interface IHolidayRepository extends CrudRepository<DiaFeriado, Integer> {

    Optional<DiaFeriado> findByDiaAndMes(Integer dia, Integer mes);

    default void validateHoliday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.findByDiaAndMes(
                calendar.get(DAY_OF_MONTH),
                calendar.get(MONTH))
                .orElseThrow(this::dateIsAHolidayException);
    }

    default LogicBusinessException dateIsAHolidayException(){
        return new LogicBusinessException(FECHA_DIA_FERIADO_24);
    }
}
