CREATE DATABASE IF NOT EXISTS devsu;

USE devsu;

CREATE TABLE `persona` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `identificacion` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contrasena` varchar(255) DEFAULT NULL,
  `estado` tinyint(1) NOT NULL,
  `persona_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
);


CREATE TABLE cuenta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta BIGINT NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(255) NOT NULL,
    saldo_inicial DECIMAL(21, 2) NOT NULL,
    estado TINYINT(1) NOT NULL,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

CREATE TABLE movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    tipo_movimiento VARCHAR(255) NOT NULL,
    valor DECIMAL(21, 2) NOT NULL,
    saldo DECIMAL(21, 2) NOT NULL,
    cuenta_id BIGINT NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta (id)
);