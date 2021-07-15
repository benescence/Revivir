/*
- Datos fundamentales para comenzar a utilizar la aplicacion
- Ejecutar este codigo en orden despues de ejecutar el codigo de creacion de tablas
- Representan datos de listas predefinidas o valores de primera ejecucion
- No deben insertarse mas datos en estas tablas a parte de estos
*/



-- INSERT TIPOS DE FALLECIMIENTO
INSERT INTO rev_tipo_fallecimiento (ID, descripcion) VALUES
	(1, "TRAUMATICO"),
	(2, "NO TRAUMATICO")
;



-- INSERT  ROLES
INSERT INTO rev_roles (ID, descripcion) VALUES
	(1, "SUPERVISOR"),
	(2, "ADMINISTRATIVO")
;



-- INSERT SECTORES
INSERT INTO rev_sector (ID, descripcion) VALUES
	(1, 'SEPULTURAS'),
	(2, 'PALMERAS'),
	(3, 'NICHERA'),
	(4, 'CENIZARIO'),
	(5, 'BOVEDA'),
	(6, 'DEPOSITO'),
	(7, 'OTRO CMENTERIO')
;



-- INSERT PERIODOS
INSERT INTO rev_periodos(ID, descripcion) VALUES
	(1, 'UNMES'),
	(2,'TRESMESES'),
	(3,'SEISMESES'),
	(4,'DOCEMESES'),
	(5,'VEINTICUATROMESES'),
	(6,'TTREINTAYSEISMESES'),
	(7,'CUARENTAYOCHOMESES')
;



-- INSERT SUB SECTORES
INSERT INTO rev_subsector (ID, sector, descripcion) VALUES
	(1, 1, 'ADULTOS'),
	(2, 1, 'ANGELITOS'),
	(3, 1, 'COMPRADA'),
	(4, 1, 'INDIGENTES'),
	(5, 2, 'PALMERAS ATAUD'),
	(6, 2, 'PALMERAS CENIZAS'),
	(7, 2, 'PALMERAS RESTOS'),
	(8, 2, 'PALMERAS SEPULTURA'),
	(9, 3, 'NICHERA'),
	(10, 4, 'CENIZARIO'),
	(11, 5, 'BOVEDA'),
	(12, 6, 'DEPOSITO 1'),
	(13, 6, 'DEPOSITO 2'),
	(14, 6, 'DEPOSITO 3'),
	(15, 7, 'OTRO CMENTERIO')
;



-- INSERT USUARIO INICIAL
INSERT INTO rev_usuarios (rol, usuario, password) VALUES 
	(1, 'admin', 'admin')
;



