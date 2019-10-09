delete from CoordinadorEntity;
delete from IncidenteEntity;
delete from TecnicoEntity;
delete from ActuacionEntity;
delete from EmpleadoEntity;

-- Poblar tablas Coordinador.

INSERT INTO CoordinadorEntity(name, password, username) 
VALUES('David', '1234', 'David');

INSERT INTO CoordinadorEntity(name, password, username) 
VALUES('Andres', 'ABC987', 'Andres');

INSERT INTO CoordinadorEntity(name, password, username) 
VALUES('Adriana', 'qwerty', 'Adriana');
