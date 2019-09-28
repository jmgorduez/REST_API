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

	@Autowired
    IContributionIntentionRepository contributionIntentionRepository;
	@Autowired
	protected IPersonRepository personRepository;
	@Autowired
	protected IPersonIdentificationRepository personIdentificationRepository;
	@Autowired
	protected IProductRepository productRepository;
	@Autowired
	protected IClientRepository clientRepository;

	//Solo para dummy
	public static final Long _1234 = 1234l;
	public static final Integer NUM_LICENCIA = 1;
	public static final String _12345678910 = "12345678910";
	public static final String APV01 = "APV01";
	public static final String COD_EMPRESA = "1";
	public static final String FONDO_APV01 = "Fondo APV01";
	public static final Integer _4321 = 4321;
	public static final Long _1111 = 1111l;
	public static final String _11111111111111111111111111111111111 = "11111111111111111111111111111111111";
	public static final BigDecimal _1000 = new BigDecimal(1000).setScale(2);
	public static final String APV0000000001 = "APV0000000001";
	public static final String APV0000000002 = "APV0000000002";

	public static final Persona PERSONA_1234 = new Persona(new PersonaPK(NUM_LICENCIA,
			_1234), "Juan Manuel Garcia", getDate("2000-03-01"), EnumSiNo.N);
	public static final PersonaIdentificacion PERSONA_1234_DUI_ID = new PersonaIdentificacion(
			new PersonaIdentificacionPK(NUM_LICENCIA, _1234, DUI_CODE), _12345678910);
	public static final PersonaIdentificacion PERSONA_1234_CI_ID = new PersonaIdentificacion(
			new PersonaIdentificacionPK(NUM_LICENCIA, _1234, "3"), _12345678910);
	public static final Producto PRODUCTO_APV01 = new Producto(new ProductoPK(NUM_LICENCIA, COD_EMPRESA, APV01), FONDO_APV01, _4321);
	public static final IntencionAporte INTENCION_APORTE_1 = new IntencionAporte(new IntencionAportePK(NUM_LICENCIA, COD_EMPRESA, APV01, 1l),
			_11111111111111111111111111111111111, _1234, _1000, APV0000000001, PEN);
	public static final Cliente CLIENTE_1234_APV0000000001 = new Cliente(new ClientePK(NUM_LICENCIA, COD_EMPRESA, APV01, _1234, PAR, 1),
			A, APV0000000001);
	public static final Cliente CLIENTE_1234_APV0000000002 = new Cliente(new ClientePK(NUM_LICENCIA, COD_EMPRESA, APV01, _1234, PAR, 2),
			A, APV0000000002);


	public static void main(String[] args) {
		SpringApplication.run(SavingsFundContributionsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Solo para dummy
		personRepository.save(PERSONA_1234);
		personIdentificationRepository.save(PERSONA_1234_DUI_ID);
		personIdentificationRepository.save(PERSONA_1234_CI_ID);
		productRepository.save(PRODUCTO_APV01);
		clientRepository.save(CLIENTE_1234_APV0000000001);
		clientRepository.save(CLIENTE_1234_APV0000000002);
		contributionIntentionRepository.save(INTENCION_APORTE_1);
	}

	//Solo para dummy
	private static Date getDate(String value) {
		try {
			return new SimpleDateFormat(YYYY_MM_DD).parse(value);
		} catch (ParseException e) {
			return null;
		}
	}
}
