/*
- Codigo para crear las tablas del sistema
- Ejecutar el codigo en orden para respetar las FK
- Nomesclatura: rev_[nombre de tabla]
*/



-- NUEVA BASE DE DATOS
-- Cuidado de no dropear la base de datos de produccion
-- Usar solo en testing
DROP DATABASE IF EXISTS revivir;
CREATE DATABASE revivir;
USE revivir;
 
 
 
-- ESQUEMA ROLES
CREATE TABLE rev_roles (
	ID INT(1),
	descripcion VARCHAR(20) NOT NULL,
	PRIMARY KEY (ID)
);



-- ESQUEMAS USUARIOS
CREATE TABLE rev_usuarios (
	ID INT(10) AUTO_INCREMENT,
	rol INT(1) NOT NULL,
	usuario VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (rol) REFERENCES rev_roles(ID)
);



-- ESQUEMAS SECTOR
CREATE TABLE rev_sector (
	ID INT(10) NOT NULL,
	descripcion VARCHAR(20) NOT NULL,
    PRIMARY KEY (ID)
);



-- ESQUEMAS SUBSECTOR
CREATE TABLE rev_subsector (
	ID INT(10) NOT NULL,
	sector INT(10) NOT NULL,
    descripcion VARCHAR(20) NOT NULL,
    PRIMARY KEY (ID),
	FOREIGN KEY (sector) REFERENCES rev_sector(ID)
);



-- ESQUEMAS UBICACIONES
CREATE TABLE rev_ubicaciones (
	ID INT(10) AUTO_INCREMENT,
	subsector INT(10) NOT NULL,
	circ INT(10) NULL,
	seccion VARCHAR(10) NULL,
	macizo INT(10) NULL,
	parcela INT(10) NULL,
	fila INT(10) NULL,
	unidad INT(10) NULL,
	nicho INT(10) NULL,
	mueble INT(10) NULL,
	sepultura INT(10) NULL,
	inhumacion INT(10) NULL,
	cementerio VARCHAR(11) NULL,
	vencimiento DATE NULL,
	bis_macizo BOOLEAN NULL,
	bis BOOLEAN NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (subsector) REFERENCES rev_subsector(ID)
);



-- ESQUEMA TIPO DE FALLECIMIENTO
CREATE TABLE rev_tipo_fallecimiento (
	ID INT(1),
	descripcion VARCHAR(20) NOT NULL,
	PRIMARY KEY (ID)
);



-- ESQUEMAS FALLECIDOS
CREATE TABLE rev_fallecidos (
	ID INT(10) AUTO_INCREMENT,
	tipo_fallecimiento INT(1) NOT NULL,
	cod_fallecido INT(11) NOT NULL,
	ubicacion INT(10) NOT NULL,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	DNI VARCHAR(11) NULL,
	cocheria VARCHAR(20) NULL,
	fecha_fallecimiento DATE NULL,
	fecha_ingreso DATE NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (tipo_fallecimiento) REFERENCES rev_tipo_fallecimiento(ID),
	FOREIGN KEY (ubicacion) REFERENCES rev_ubicaciones(ID)
);



-- ESQUEMAS MOVIMIENTOS
CREATE TABLE rev_movimientos (
	ID INT(10) AUTO_INCREMENT,
	fallecido INT(10) NOT NULL,
	antigua_ubicacion VARCHAR(150) NOT NULL,
	causa_traslado VARCHAR(150) NOT NULL,
	observaciones VARCHAR(50) NULL,
	fecha_movimiento DATE NOT NULL,
	FOREIGN KEY (fallecido) REFERENCES rev_fallecidos(ID),
	PRIMARY KEY (ID)
);



-- ESQUEMA CLIENTES
CREATE TABLE rev_clientes (
	ID INT(10) AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	DNI VARCHAR(10) NULL,
	telefono VARCHAR(20) NULL,
	email VARCHAR(50) NULL,
	domicilio VARCHAR(100) NULL,
	PRIMARY KEY (ID)
);



-- ESQUEMAS RESPONSABLES
CREATE TABLE rev_responsables (
	ID INT(10) AUTO_INCREMENT,
	cliente INT(10) NOT NULL,
	fallecido INT(10) NOT NULL,
	observaciones VARCHAR(100) NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (cliente) REFERENCES rev_clientes(ID),
	FOREIGN KEY (fallecido) REFERENCES rev_fallecidos(ID)
);



-- ESQUEMAS SERVICIOS
CREATE TABLE rev_servicios (
	ID INT(10) AUTO_INCREMENT,
	codigo VARCHAR(50) NOT NULL,
	nombre VARCHAR(30) NOT NULL,
	descripcion VARCHAR(100) NULL,
	importe FLOAT(10) NOT NULL,
	historico BOOLEAN NOT NULL,
	PRIMARY KEY (ID)
);



-- ESQUEMAS CARGOS
CREATE TABLE rev_cargos (
	ID INT(10) AUTO_INCREMENT,
	fallecido INT(10) NOT NULL,
	servicio INT(10) NOT NULL,
	observaciones VARCHAR(100) NULL,
	pagado BOOLEAN NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (servicio) REFERENCES rev_servicios(ID),
	FOREIGN KEY (fallecido) REFERENCES rev_fallecidos(ID)
);



-- ESQUEMAS PAGOS
CREATE TABLE rev_pagos (
	ID INT(10) AUTO_INCREMENT,
	cargo INT(10) NOT NULL,
	importe DOUBLE(10, 2) NOT NULL,
	observaciones VARCHAR(100) NULL,
	fecha DATE NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (cargo) REFERENCES rev_cargos(ID)
);



-- ESQUEMAS PERIODOS
CREATE TABLE rev_periodos (
	ID INT(1),
	descripcion VARCHAR(20) NOT NULL,
	PRIMARY KEY (ID)
);



-- ESQUEMAS EXPENSAS
CREATE TABLE rev_expensas (
	ID INT(10) AUTO_INCREMENT,
	responsable INT(10) NOT NULL,
	periodo INT(10) NOT NULL,
	ubicacion INT(10) NOT NULL,
	fecha_vencimiento DATE NOT NULL,
	importe DOUBLE(10, 2) NOT NULL,
	observaciones VARCHAR(100) NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (responsable) REFERENCES rev_clientes(ID),
	FOREIGN KEY (periodo) REFERENCES rev_cargos(ID),
    FOREIGN KEY (ubicacion) REFERENCES rev_ubicaciones(ID)
);



-- ESQUEMAS UBICACIONES TOTALES
CREATE TABLE rev_ubicaciones_totales (
	ID INT(10) AUTO_INCREMENT,
	subsector INT(10) NOT NULL,
	circ INT(10) NULL,
	seccion VARCHAR(10) NULL,
	macizo INT(10) NULL,
	parcela INT(10) NULL,
	fila INT(10) NULL,
	unidad INT(10) NULL,
	nicho INT(10) NULL,
	mueble INT(10) NULL,
	sepultura INT(10) NULL,
	inhumacion INT(10) NULL,
	bis_macizo BOOLEAN NULL,
	bis BOOLEAN NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (subsector) REFERENCES rev_subsector(ID)
);


