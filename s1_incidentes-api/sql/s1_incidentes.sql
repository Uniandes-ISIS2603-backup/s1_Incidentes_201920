delete from ActuacionEntity;
delete from IncidenteEntity;
delete from TecnicoEntity;
delete from EmpleadoEntity;
delete from CoordinadorEntity;



-- Poblar tablas Coordinador.
SELECT * FROM  IncidenteEntity;
INSERT INTO CoordinadorEntity(name, password, username,id) 
VALUES('David', '1234', 'David',769);

INSERT INTO CoordinadorEntity(name, password, username,id) 
VALUES('Andres', 'ABC987', 'Andres',770);

INSERT INTO CoordinadorEntity(name, password, username,id) 
VALUES('Adriana', 'qwerty', 'Adriana',771);

-- Poblar tablas Empleado.

INSERT INTO EmpleadoEntity(nombre, password, username) 
VALUES('Edison', 'light', 'Edison');

INSERT INTO EmpleadoEntity(nombre, password, username) 
VALUES('Albert', '4LB3RT', 'Albert');

INSERT INTO EmpleadoEntity(nombre, password, username) 
VALUES('Jeremy', 'OTV', 'Toast');

-- Poblar tablas Incidente
INSERT INTO IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion
,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id)
VALUES(1000000,'2018-03-29 13:34:00.000','2019-03-29 13:34:00.000',
'descripcion1','observacion1',10,'categoria1','prioridad1',1,1,'equipo1',769);
INSERT INTO IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion
,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id)
VALUES(1000001,'2018-03-29 13:34:00.000','2019-03-29 13:34:00.000',
'descripcion2','observacion2',10,'categoria2','prioridad2',1,1,'equipo2',769);
INSERT INTO IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion
,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id)
VALUES(1000002,'2018-03-29 13:34:00.000','2019-03-29 13:34:00.000',
'descripcion3','observacion3',10,'categoria3','prioridad3',1,1,'equipo3',771);