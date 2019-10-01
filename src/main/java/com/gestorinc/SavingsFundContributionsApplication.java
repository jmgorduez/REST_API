package com.gestorinc;

import com.gestorinc.repository.*;
import com.gestorinc.repository.entity.*;
import com.gestorinc.repository.entity.enums.EnumSiNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.gestorinc.repository.entity.enums.EnumEstadoIntencionAporte.PEN;
import static com.gestorinc.repository.entity.enums.EnumEstadoParticipe.A;
import static com.gestorinc.repository.entity.enums.EnumTipoCliente.PAR;
import static com.gestorinc.utils.Constants.DUI_CODE;
import static com.gestorinc.utils.Constants.YYYY_MM_DD;

@SpringBootApplication
public class SavingsFundContributionsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SavingsFundContributionsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
