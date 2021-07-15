/*
- Codigo completo para crear las vistas del sistema
- Ejecutar primero el codigo para crear tablas
- Nomesclatura rev_v_[nombre de vista]
*/



-- VISTA UBICACIONES LIBRES
-- Devuelve lo que hay en ubicaciones totales restando lo que hay en ubicaciones (ubicaciones ocupadas)

CREATE VIEW rev_v_ubicaciones_libres AS
	SELECT 
		UT.ID,
		UT.subsector,
		UT.circ,
		UT.seccion,
		UT.macizo,
		UT.parcela,
		UT.fila,
		UT.unidad,
		UT.nicho,
		UT.mueble,
		UT.sepultura,
		UT.inhumacion,
		UT.bis_macizo,
		UT.bis
	FROM rev_ubicaciones_totales UT
		LEFT JOIN rev_ubicaciones U ON IFNULL(UT.fila, -1) = IFNULL(U.fila, -1)
			AND IFNULL(UT.subsector, -1) = IFNULL(U.subsector, -1)
			AND IFNULL(UT.circ, -1) = IFNULL(U.circ, -1)
			AND IFNULL(UT.seccion, -1) = IFNULL(U.seccion, -1)
			AND IFNULL(UT.macizo, -1) = IFNULL(U.macizo, -1)
			AND IFNULL(UT.parcela, -1) = IFNULL(U.parcela, -1)
			AND IFNULL(UT.unidad, -1) = IFNULL(U.unidad, -1)
			AND IFNULL(UT.nicho, -1) = IFNULL(U.nicho, -1)
			AND IFNULL(UT.mueble, -1) = IFNULL(U.mueble, -1)
			AND IFNULL(UT.sepultura, -1) = IFNULL(U.sepultura, -1)
			AND IFNULL(UT.inhumacion, -1) = IFNULL(U.inhumacion, -1)
			AND IFNULL(UT.bis_macizo, -1) = IFNULL(U.bis_macizo, -1)
			AND IFNULL(UT.bis, -1) = IFNULL(U.bis, -1)
	WHERE U.ID IS NULL
;



-- VISTA FALLECIDOS
-- Devuelve a los fallecidos con los datos de su ubicacion

CREATE VIEW rev_v_fallecidos AS 
	SELECT
		F.id,
		F.tipo_fallecimiento,
		F.cod_fallecido,
		F.ubicacion,
		F.nombre,
		F.apellido,
		F.DNI,
		F.cocheria,
		F.fecha_fallecimiento,
		F.fecha_ingreso,
		U.vencimiento,
		U.subsector,
		U.circ,
		U.seccion,
		U.macizo,
		U.parcela,
		U.fila,
		U.unidad,
		U.nicho,
		U.mueble,
		U.sepultura,
		U.inhumacion,
		U.cementerio,
		U.bis_macizo,
		U.bis
	FROM rev_fallecidos F
		LEFT JOIN rev_ubicaciones U ON F.ubicacion = U.id
;



-- VISTA CLIENTE NOTIFICACIONES
-- Devuelve a los clientes con los datos de los fallecidos a su cargo
-- Si un cliente tiene mas de un fallecido a su cargo devolvera a ese cliente una vez por cada uno
-- Se anexan tambien los datos de la ubicacion del fallecido

CREATE VIEW rev_v_cliente_notificaciones AS 
	SELECT
		C.ID AS cli_ID,
		C.nombre AS cli_nombre,
		C.apellido AS cli_apellido,
		C.DNI AS cli_dni,
		C.telefono AS cli_telefono,
		C.email,
		C.domicilio,
		F.id,
		F.tipo_fallecimiento,
		F.cod_fallecido,
		F.ubicacion,
		F.nombre,
		F.apellido,
		F.DNI,
		F.cocheria,
		F.fecha_fallecimiento,
		F.fecha_ingreso,
		U.vencimiento,
		U.subsector,
		U.circ,
		U.seccion,
		U.macizo,
		U.parcela,
		U.fila,
		U.unidad,
		U.nicho,
		U.mueble,
		U.sepultura,
		U.inhumacion,
		U.cementerio,
		U.bis_macizo,
		U.bis
	FROM rev_fallecidos F
		LEFT JOIN rev_responsables R ON F.ID = R.fallecido 
		LEFT JOIN rev_clientes C ON C.ID = R.cliente
		LEFT JOIN rev_ubicaciones U ON F.ubicacion = U.id
;


