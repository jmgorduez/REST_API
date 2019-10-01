INSERT INTO ADM_PERSONA
  (NUM_LICENCIA, COD_PERSONA, NOMBRES, COD_TIPO_EMPLEO, CATEGORIA, NOMBRE1, NOMBRE2,
    	APELLIDOPATERNO, APELLIDOMATERNO, COD_ESTADO_PERSONA, FECHA_NACIMIENTO, COD_PAIS,PAIS_LOCAL, AUD_VERSION)
VALUES  (1, 1234, 'Juan Manuel Garcia', '1', '1', 'Juan', 'Manuel',
  'Garcia', '', '1', to_date('05 JUN 1985'), '1','S', 1);
INSERT INTO ADM_PERSONA
  (NUM_LICENCIA, COD_PERSONA, NOMBRES, COD_TIPO_EMPLEO, CATEGORIA, NOMBRE1, NOMBRE2,
    	APELLIDOPATERNO, APELLIDOMATERNO, COD_ESTADO_PERSONA, FECHA_NACIMIENTO, COD_PAIS,PAIS_LOCAL, AUD_VERSION)
VALUES  (1, 1235, 'Jorge Manuel Garcia', '1', '1', 'Jorge', 'Manuel',
  'Garcia', '', '1', to_date('05 JUN 1985'), '1','S', 1);
INSERT INTO ADM_PERSONA
  (NUM_LICENCIA, COD_PERSONA, NOMBRES, COD_TIPO_EMPLEO, CATEGORIA, NOMBRE1, NOMBRE2,
    	APELLIDOPATERNO, APELLIDOMATERNO, COD_ESTADO_PERSONA, FECHA_NACIMIENTO, COD_PAIS,PAIS_LOCAL, AUD_VERSION)
VALUES  (1, 1236, 'Carla Garcia', '1', '1', 'Carla', '',
  'Garcia', '', '1', to_date('05 JUN 1985'), '1','N', 1);

INSERT INTO ADM_PERSONA_IDENTIFICACION
  (NUM_LICENCIA,COD_PERSONA,COD_TIPO_IFICACION,IDENTIFICACION,	PREFERIDO,FECHA_CADUCIDAD,AUD_VERSION)
VALUES  (1, 1234, '10', '12345678910', 'N', NULL, 1);
INSERT INTO ADM_PERSONA_IDENTIFICACION
  (NUM_LICENCIA,COD_PERSONA,COD_TIPO_IFICACION,IDENTIFICACION,	PREFERIDO,FECHA_CADUCIDAD,AUD_VERSION)
VALUES  (1, 1235, '3', '12345678912', 'N', NULL, 1);
INSERT INTO ADM_PERSONA_IDENTIFICACION
  (NUM_LICENCIA,COD_PERSONA,COD_TIPO_IFICACION,IDENTIFICACION,	PREFERIDO,FECHA_CADUCIDAD,AUD_VERSION)
VALUES  (1, 1236, '3', '12345678913', 'N', NULL, 1);

INSERT INTO ADM_PRODUCTO
  (NUM_LICENCIA, COD_EMPRESA,COD_PRODUCTO,COD_TIPO_PRODUCTO,COD_ESTADO_PRODUCTO,NOMBRE,
     	HABILITADO_BE,AUD_VERSION,COD_MONEDA,INACTIVAR_TMP,DETALLAR_PARTICIPACION,TIPO_ACTUALIZACION,
     	HABILITADO_INFO_CTAS_BANCARIAS,HABILITADO_INFO_INVERSIONES,HABILITADO_INFO_CONTABLE,
     	HABILITADO_DOCUMENTOS,HABILITADO_INFO_PROYECTOS_INMO,HABILITADO_INFO_INMUEBLES,
     	HABILITADO_INFO_BIE_MUEBLES,MANEJA_OPERACIONES,HABILITADO_INFO_BIENES,HABILITADO_MOVIMIENTOS_PARTIC,
     	HABILITADO_REPORTES,FECHA_CORTE_CIERRE,GLN,TIEMPO_VALIDEZ_NPE)
VALUES  (1, '1', 'APV01', '1', 'ACT', 'Fondo de ahorro voluntario', 'S', 1, 'USD', 'N', 'N', 'CON',
'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', 'S', NULL, 4321, 3);

INSERT INTO ADM_CLIENTE
   (NUM_LICENCIA,COD_EMPRESA,COD_PRODUCTO,COD_PERSONA,TIPO_CLIENTE,SEC_PERSONA_TIPO_CLIENTE,
     	FECHA_INGRESO,FECHA_SALIDA,ESTADO_FID_BEN,ESTADO_PARTICIPE,NUMERO_CUENTA)
VALUES  (1, '1', 'APV01', 1234, 'PAR', 1, NULL, NULL, 'INA', 'A', 'APV0000000001');
INSERT INTO ADM_CLIENTE
   (NUM_LICENCIA,COD_EMPRESA,COD_PRODUCTO,COD_PERSONA,TIPO_CLIENTE,SEC_PERSONA_TIPO_CLIENTE,
     	FECHA_INGRESO,FECHA_SALIDA,ESTADO_FID_BEN,ESTADO_PARTICIPE,NUMERO_CUENTA)
VALUES  (1, '1', 'APV01', 1234, 'PAR', 2, NULL, NULL, 'INA', 'A', 'APV0000000002');

INSERT INTO FID_INTENCION_APORTE_BE
    (NUM_LICENCIA,COD_EMPRESA,COD_PRODUCTO,SEC_INTENCION,COD_PERSONA,CUENTA_PARTICIPE,
     ESTADO,MONTO,COD_MONEDA,FECHA_SOLICITUD,FECHA_VENCIMIENTO,NPE,AUD_USUARIO_INGRESO,
     AUD_FECHA_INGRESO,AUD_IP_INGRESO,AUD_COD_CANAL_INGRESO,AUD_VERSION)
VALUES  (1, '1', 'APV01', 1, 1234, 'APV0000000001', 'PEN', 1000, 'USD', NULL, NULL,
     '11111111111111111111111111111111111', NULL, NULL, NULL, NULL, 1);
INSERT INTO FID_INTENCION_APORTE_BE
    (NUM_LICENCIA,COD_EMPRESA,COD_PRODUCTO,SEC_INTENCION,COD_PERSONA,CUENTA_PARTICIPE,
     ESTADO,MONTO,COD_MONEDA,FECHA_SOLICITUD,FECHA_VENCIMIENTO,NPE,AUD_USUARIO_INGRESO,
     AUD_FECHA_INGRESO,AUD_IP_INGRESO,AUD_COD_CANAL_INGRESO,AUD_VERSION)
VALUES  (1, '1', 'APV01', 2, 1234, 'APV0000000001', 'PAG', 1000, 'USD', NULL, NULL,
     '11111111111111111111111111111111112', NULL, NULL, NULL, NULL, 1);
INSERT INTO FID_INTENCION_APORTE_BE
    (NUM_LICENCIA,COD_EMPRESA,COD_PRODUCTO,SEC_INTENCION,COD_PERSONA,CUENTA_PARTICIPE,
     ESTADO,MONTO,COD_MONEDA,FECHA_SOLICITUD,FECHA_VENCIMIENTO,NPE,AUD_USUARIO_INGRESO,
     AUD_FECHA_INGRESO,AUD_IP_INGRESO,AUD_COD_CANAL_INGRESO,AUD_VERSION)
VALUES  (1, '1', 'APV01', 3, 1234, 'APV0000000001', 'VEN', 1000, 'USD', NULL, NULL,
     '11111111111111111111111111111111113', NULL, NULL, NULL, NULL, 1);
INSERT INTO FID_INTENCION_APORTE_BE
    (NUM_LICENCIA,COD_EMPRESA,COD_PRODUCTO,SEC_INTENCION,COD_PERSONA,CUENTA_PARTICIPE,
     ESTADO,MONTO,COD_MONEDA,FECHA_SOLICITUD,FECHA_VENCIMIENTO,NPE,AUD_USUARIO_INGRESO,
     AUD_FECHA_INGRESO,AUD_IP_INGRESO,AUD_COD_CANAL_INGRESO,AUD_VERSION)
VALUES  (1, '1', 'APV01', 4, 1234, 'APV0000000001', 'NTF', 1000, 'USD', NULL, NULL,
     '11111111111111111111111111111111114', NULL, NULL, NULL, NULL, 1);
INSERT INTO FID_INTENCION_APORTE_BE
    (NUM_LICENCIA,COD_EMPRESA,COD_PRODUCTO,SEC_INTENCION,COD_PERSONA,CUENTA_PARTICIPE,
     ESTADO,MONTO,COD_MONEDA,FECHA_SOLICITUD,FECHA_VENCIMIENTO,NPE,AUD_USUARIO_INGRESO,
     AUD_FECHA_INGRESO,AUD_IP_INGRESO,AUD_COD_CANAL_INGRESO,AUD_VERSION)
VALUES  (1, '1', 'APV01', 6, 1235, 'APV0000000001', 'PEN', 1000, 'USD', NULL, NULL,
     '11111111111111111111111111111111116', NULL, NULL, NULL, NULL, 1);