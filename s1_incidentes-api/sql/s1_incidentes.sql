delete from ActuacionEntity;
delete from IncidenteEntity;
delete from TecnicoEntity;
delete from EmpleadoEntity;
delete from CoordinadorEntity;

------------------------------------------------------
------ Poblar tablas Coordinador.---------------------
------------------------------------------------------

insert into CoordinadorEntity(name, password, username,id) values('David', '1234', 'David',769);
insert into CoordinadorEntity(name, password, username,id) values('Andres', 'ABC987', 'Andres',770);
insert into CoordinadorEntity(name, password, username,id) values('Adriana', 'qwerty', 'Adriana',771);
insert into CoordinadorEntity(name, password, username,id) values('Pedro', 'clavePedro', 'pedrito',772);
insert into CoordinadorEntity(name, password, username,id) values('Luis', 'olo', 'Llllll',773);
insert into CoordinadorEntity(name, password, username,id) values('Angelica', 'amort', 'Yisus',774);

------------------------------------------------------
--------- Poblar tablas Empleado.---------------------
------------------------------------------------------

insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (120, 'Rachelle', '3o4CcvDkSd', 'rcounsell3b', 4);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (121, 'Cash', '5shUY4Hk1N2', 'cjorgensen3c', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (122, 'Scotty', 'paaQEH', 'sgodlonton3d', 1);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (123, 'Marwin', 'MlG55gx', 'mconcannon3e', 2);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (124, 'Dario', 'A1kgLBDP', 'dkield3f', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (125, 'Ancell', 'hUbPuMYRT', 'aboast3g', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (126, 'Ninon', 'ZCDJSnVQMR', 'nfleckno3h', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (127, 'Brooks', '4a06qo', 'brupprecht3i', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (128, 'Fanya', '8o9khtN', 'fscottrell3j', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (129, 'Cross', 'aiFuvwmQX', 'cleither3k', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (130, 'Justinian', 'cFeVYo9c', 'jbrunker3l', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (131, 'Cleo', 'KlaFyecek3', 'clangfield3m', 3);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (132, 'Alvie', 'smYwG0D', 'aolexa3n', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (133, 'Kelcie', 'f9f8mU', 'kbrabant3o', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (134, 'Carmelle', 'HaSEoWg', 'clain3p', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (135, 'Elga', '32kJWc6phzAH', 'ebuntine3q', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (136, 'Marj', '1TOtsk', 'mmcallaster3r', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (137, 'Shel', 'XIbMoD', 'swollen3s', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (138, 'Hartley', 'UyCi7cr', 'hwintersgill3t', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (139, 'Marigold', 'wwm8NG', 'mstables3u', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (140, 'Bili', 'KYyDOOGuYuE', 'byanukhin3v', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (141, 'Dan', 'OFppVm', 'druby3w', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (142, 'Pauly', 'hSSzTn', 'plukovic3x', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (143, 'Moreen', 'FnpODOdcoHrX', 'manster3y', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (144, 'Seward', 'pu4JKp2ojLWw', 'slongshaw3z', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (145, 'Mathian', 'mUJiMvW', 'mvasilik40', 1);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (146, 'Michelina', 'MmetQX', 'mshurrock41', 1);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (147, 'Edythe', 'Qi3Ha04yVf', 'ekemet42', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (148, 'Tasia', 'LnbDs4xq0Z', 'tyarnell43', 0);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (149, 'Wenona', 'VszzeZc', 'wleonida44', 2);
insert into EmpleadoEntity (id, nombre, password, username, numIncidentes) values (150, 'Piggy', 'enX8xkVBN', 'pstegel45', 3);

------------------------------------------------------
--------- Poblar tablas Tecnico.----------------------
------------------------------------------------------

insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (129, 'fp1EpOMN', 'mdreigher3k', 0, 'SW_SA', 769);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (130, '5CF6hp', 'smacknish3l', 0, 'SW_SA', 769);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (131, 'pdvrUeCWwT', 'meager3m', 0, 'SW_SO', 769);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (132, 'gm4QHpB', 'tcrighton3n', 0, 'SW_SO', 769);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (133, 'FdJBqL0m', 'dpaulich3o', 0, 'HARWARE', 769);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (134, 'INkoEOkkr2', 'dgiercke3p', 0, 'SW_SA', 771);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (135, 'ovPJCS9hpK', 'cblackford3q', 0, 'SW_SO', 771);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (136, 'mhLAgB8SM', 'mleuren3r', 0, 'HARWARE', 772);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (137, 'frGB4xq', 'dlabone3s', 0, 'SW_SA', 773);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (138, 'y1ybgOXE', 'clinge3t', 0, 'SW_SA', 774);

------------------------------------------------------
--------- Poblar tablas Incidente.--------------------
------------------------------------------------------

INSERT INTO EmpleadoEntity(nombre, password, username) 
VALUES('Albert', '4LB3RT', 'Albert');

INSERT INTO EmpleadoEntity(nombre, password, username) 
VALUES('Jeremy', 'OTV', 'Toast');
INSERT INTO EmpleadoEntity(nombre, password, username, id) 
VALUES('Jeremy', 'OTV', 'Toast',6000000);

INSERT INTO TecnicoEntity(username, password, id) 
VALUES('Jeremy',  'Toast',6000000);
select * from tecnicoentity;
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
