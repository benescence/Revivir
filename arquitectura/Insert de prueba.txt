INSERT INTO rev_clientes (nombre, apellido, DNI, telefono, email, domicilio) VALUES
("Carlos", "Caballero", "1", null, "carlos@gmail.com", "San Miguel"),
("Jorge", "Caballero", "2", "44556699", "jorge@gmail.com", "Paraguay"),
("Benito", "Molina", "3", "5555555", "benito@hotmail.com", "Joce C. Paz"),
("Marcelo", "Gutierrez", "4", null, null, "Argentina"),
("Rick", "Sanchez", "4", "42", null, "En otra dimension");

INSERT INTO rev_servicios (codigo, nombre, importe, historico, descripcion) VALUES
("01", "Entierro", 1000.50, false, "Entierro simple"),
("02", "Entierro completo", 900.50, false, "Entierro que incluye lapida y funeral"),
("03", "Limpieza", 0.50, false, "Arreglos luego de una tormenta"),
("04", "Asesoria legal", 5000, false, "Solo incluye el contacto de un abogado"),
("05", "Trasporte", 200.50, false, "Trasporte del fallecido desde el velorio hasta el cementerio"),
("05", "Trasporte", 50.50, true, "Trasporte del fallecido desde el velorio hasta el cementerio");

INSERT INTO rev_ubicaciones (subsector, circ, seccion, macizo, parcela, fila, unidad, nicho, mueble, sepultura,
	inhumacion, cementerio, vencimiento, bis_macizo, bis) VALUES
(1, null, "Azul", 15, null, 1, null, null, 7, null, null, null, '2018-10-20', null, null),
(2, null, null, 15, null, 1, null, 8, 7, null, null, null, null, null, 45),
(3, null, "Roja", null, null, 1, null, null, 7, null, null, null, '2018-11-19', null, null),
(4, null, "Verde", 10, null, 1, 7, null, 7, null, null, "La trinidad", null, null, null),
(5, null, null, 15, null, 1, null, null, 7, null, null, null, null, null, null);

INSERT INTO rev_fallecidos (tipo_fallecimiento,cod_fallecido, ubicacion, nombre, apellido, dni, cocheria, fecha_fallecimiento, fecha_ingreso) VALUES
(1, 1 ,  1, "Goku", "Son", "1", "Cell y amigos", '2018-10-20', '2018-10-22'),
(1, 2, 2, "Leonardo", "Di Caprio", "2", "Titanic", '1912-10-20', '2018-10-22'),
(1, 3, 3, "Bruce", "Willis", "3", "Armagedon", '2018-10-20', '2018-10-22'),
(1, 4, 4, "Depredador", "1", "4", "Arnold", '2018-10-20', '2018-10-22'),
(2, 5, 5, "Stan", "Lee", "5", "Marvel", '2018-10-20', '2018-10-22');

INSERT INTO rev_cargos (fallecido, servicio, observaciones, pagado) VALUES
(1, 1, "No cobrar", false),
(2, 2, null, false),
(3, 3, "No cobrar", false),
(4, 4, "Maleducado", true),
(5, 5, "Todo sube", false);

insert into rev_usuarios (rol, usuario, password) values 
(1, 'admin', 'admin'),
(2, 'super', 'super');

INSERT INTO rev_responsables (cliente, fallecido, observaciones) VALUES
(1,2,"PROBANDO RESPONSABLES1"),
(2,3,"PROBANDO RESPONSABLES1"),
(4,5,"PROBANDO RESPONSABLES1");

INSERT INTO rev_expensas (responsable, periodo, ubicacion, fecha_vencimiento, importe, observaciones) VALUES
(1, 2, 3,'2019-10-20', 880, "prueba 1"),
(2, 1, 2,'2020-11-20', 1880, "prueba 2"),
(3, 2, 4,'2019-10-20', 880.5, "prueba 3"),
(2, 3, 1,'2018-10-20', 0, "prueba 4");

INSERT INTO rev_pagos (cargo, importe, observaciones, fecha) VALUES
(1,990,"pagos 1",'2018-10-22'),
(2,1990,"pagos 2",'2018-10-22'),
(3,2990,"pagos 3",'2018-10-22'),
(2,99,"pagos 4",'2019-01-02'),
(1,99.40,"pagos 5",'2019-01-02');

INSERT INTO rev_movimientos (fallecido, antigua_ubicacion, causa_traslado, observaciones, fecha_movimiento) VALUES
(2,"tranladando 1", "no hay porque", "porque ",'2018-10-22'),
(1,"tranladando 2", "no hay porque 2", "porque ",'2018-11-23'),
(4,"tranladando 3", "no hay porque 3", "porque ",'2018-10-22'),
(3,"tranladando 4", "no hay porque 4", "porque ",'2019-01-02');


