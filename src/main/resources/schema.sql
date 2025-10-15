CREATE DATABASE HotelDB;
USE HotelDB;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    tipo_usuario ENUM('huesped', 'administrador') NOT NULL
);

CREATE TABLE hotel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    cantidad_habitaciones INT NOT NULL,
    administrador_id INT,
    FOREIGN KEY (administrador_id) REFERENCES usuario(id) ON DELETE SET NULL
);

CREATE TABLE habitacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    tamaño VARCHAR(50) NOT NULL,
    cantidad_personas INT NOT NULL,
    estado ENUM('disponible', 'ocupado') NOT NULL,
    hotel_id INT NOT NULL,
    FOREIGN KEY (hotel_id) REFERENCES hotel(id) ON DELETE CASCADE
);

CREATE TABLE reservacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    fecha_reservacion DATE NOT NULL,
    usuario_id INT NOT NULL,
    habitacion_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (habitacion_id) REFERENCES habitacion(id) ON DELETE CASCADE
);

CREATE TABLE recuperacion_datos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reservacion_id INT NOT NULL,
    usuario_id INT NOT NULL,
    fecha_ocupacion DATE NOT NULL,
    FOREIGN KEY (reservacion_id) REFERENCES reservacion(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);
