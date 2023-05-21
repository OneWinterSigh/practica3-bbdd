-- CREATE DATABASE panaderias;
USE panaderias;
CREATE TABLE Local (
    `codigo` int NOT NULL,
    `tiene_cafeteria` boolean NOT NULL,
    `direccion` varchar(100) NOT NULL,
    PRIMARY KEY(`codigo`)
);
CREATE TABLE Empleado (
    `id_empleado` int AUTO_INCREMENT NOT NULL,
    `nombre` varchar(100) NOT NULL,
    `apellido1` varchar(100) NOT NULL,
    `apellido2` varchar(100) NOT NULL,
    `n_ss` int NOT NULL,
    PRIMARY KEY(`id_empleado`)
);
CREATE TABLE Trabaja (
    `fecha_inicio` TIMESTAMP NOT NULL,
    `fecha_fin` TIMESTAMP NOT NULL,
    `codigo_local` int NOT NULL,
    `id_empleado` int NOT NULL,
    PRIMARY KEY(`fecha_inicio`),
    FOREIGN KEY(`codigo_local`) REFERENCES Local (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(`id_empleado`) REFERENCES Empleado (`id_empleado`) ON DELETE CASCADE ON UPDATE CASCADE
);