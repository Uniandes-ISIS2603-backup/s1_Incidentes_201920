delete from ActuacionEntity;
delete from IncidenteEntity;
delete from TecnicoEntity;
delete from EmpleadoEntity;
delete from CoordinadorEntity;

------------------------------------------------------
------ Poblar tablas Coordinador.---------------------
------------------------------------------------------

insert into CoordinadorEntity(name, password, username,id) values('David', '1234', 'ContraseñaSuperSegura',769);
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

insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (129, 'fp1EpOMN', 'mdreigher3k', 3, 'SW_SA', 769);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (130, '5CF6hp', 'smacknish3l', 2, 'SW_SA', 769);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (131, 'pdvrUeCWwT', 'meager3m', 1, 'SW_SO', 769);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (132, 'gm4QHpB', 'tcrighton3n', 1, 'SW_SO', 769);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (133, 'FdJBqL0m', 'dpaulich3o', 2, 'HARWARE', 769);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (134, 'INkoEOkkr2', 'dgiercke3p', 1, 'SW_SA', 771);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (135, 'ovPJCS9hpK', 'cblackford3q', 1, 'SW_SO', 771);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (136, 'mhLAgB8SM', 'mleuren3r', 2, 'HARWARE', 772);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (137, 'frGB4xq', 'dlabone3s', 1, 'SW_SA', 773);
insert into TecnicoEntity (id, password, username, numcasos, especialidad, coordinador_id) values (138, 'y1ybgOXE', 'clinge3t', 3, 'SW_SA', 774);

------------------------------------------------------
--------- Poblar tablas Incidente.--------------------
------------------------------------------------------

insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000000,'2018-03-17 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',10,'SW_SA','alta',1,0,'equipo1',769,129,120);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000001,'2018-10-29 13:34:00.000','2019-03-29 13:34:00.000','descripcion2','observacion2',9,'SW_SA','media',1,0,'equipo2',769,129,120);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000002,'2018-06-13 13:34:00.000','2019-03-29 13:34:00.000','descripcion3','observacion3',8,'SW_SA','alta',1,0,'equipo3',771,129,120);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000003,'2018-01-04 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',10,'SW_SA','alta',0,1,'equipo1',769,130,120);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000004,'2018-02-21 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',5,'SW_SA','media',1,0,'equipo1',769,130,122);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000005,'2018-03-30 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',10,'SW_SO','media',1,0,'equipo1',769,131,123);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000006,'2018-04-29 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',9,'SW_SO','media',1,0,'equipo1',769,132,123);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000007,'2018-08-19 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',9,'HARWARE','baja',1,1,'equipo1',769,133,131);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000008,'2018-07-05 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',8,'HARWARE','baja',1,0,'equipo1',769,133,131);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000009,'2018-06-14 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',10,'SW_SA','alta',1,0,'equipo1',770,134,131);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000010,'2018-05-08 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',10,'SW_SO','baja',1,0,'equipo1',770,135,145);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000011,'2018-12-05 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',10,'HARWARE','media',1,1,'equipo1',772,136,146);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000012,'2018-11-11 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',10,'HARWARE','media',1,1,'equipo1',772,136,149);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000013,'2018-10-12 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',8,'SW_SA','baja',1,0,'equipo1',773,137,149);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000014,'2018-09-21 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',6,'SW_SA','media',1,1,'equipo1',774,138,150);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000015,'2018-03-23 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',7,'SW_SA','media',1,0,'equipo1',774,138,150);
insert into IncidenteEntity(id,fechaHoraInicio,fechaHoraFinal,descripcion,observaciones,calificacion,categoria,prioridad,solucionado,reabrir,equipo,coordinador_id,tecnico_id,empleado_id)
values(1000016,'2018-02-15 13:34:00.000','2019-03-29 13:34:00.000','descripcion1','observacion1',8,'SW_SA','baja',1,0,'equipo1',774,138,150);

------------------------------------------------------
--------- Poblar tablas Actuaciones.--------------------
------------------------------------------------------

insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (101, 'que mas pues', '2019-01-01 10:00:00.000', 1000000);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (102, 'que mas pues', '2019-01-01 10:00:00.000', 1000000);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (103, 'que mas pues', '2019-01-01 10:00:00.000', 1000001);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (104, 'que mas pues', '2019-01-01 10:00:00.000', 1000002);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (105, 'que mas pues', '2019-01-01 10:00:00.000', 1000002);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (106, 'que mas pues', '2019-01-01 10:00:00.000', 1000003);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (107, 'que mas pues', '2019-01-01 10:00:00.000', 1000004);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (108, 'que mas pues', '2019-01-01 10:00:00.000', 1000005);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (109, 'que mas pues', '2019-01-01 10:00:00.000', 1000006);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (110, 'que mas pues', '2019-01-01 10:00:00.000', 1000007);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (111, 'que mas pues', '2019-01-01 10:00:00.000', 1000008);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (112, 'que mas pues', '2019-01-01 10:00:00.000', 1000009);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (113, 'que mas pues', '2019-01-01 10:00:00.000', 1000009);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (114, 'que mas pues', '2019-01-01 10:00:00.000', 1000010);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (115, 'que mas pues', '2019-01-01 10:00:00.000', 1000010);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (116, 'que mas pues', '2019-01-01 10:00:00.000', 1000011);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (117, 'que mas pues', '2019-01-01 10:00:00.000', 1000012);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (118, 'que mas pues', '2019-01-01 10:00:00.000', 1000013);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (119, 'que mas pues', '2019-01-01 10:00:00.000', 1000014);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (120, 'que mas pues', '2019-01-01 10:00:00.000', 1000015);
insert into actuacionentity (id,descripcion, fechahora, incidente_id) values (121, 'que mas pues', '2019-01-01 10:00:00.000', 1000016);
