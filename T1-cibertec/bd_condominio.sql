DROP DATABASE IF EXISTS db_condominio;
CREATE DATABASE db_condominio;
USE db_condominio;

CREATE TABLE rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE,
    estado BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    id_rol INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    correo VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

CREATE TABLE trabajador (
    id_trabajador INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    correo VARCHAR(150) NOT NULL UNIQUE,
    cargo VARCHAR(50) NOT NULL,
    turno VARCHAR(20) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    
    id_usuario INT NOT NULL,
    fecha_reserva DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    estado ENUM('PENDIENTE','APROBADA','RECHAZADA','FINALIZADA') DEFAULT 'PENDIENTE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reserva_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE departamento (
    id_departamento INT AUTO_INCREMENT PRIMARY KEY,
   
    numero VARCHAR(20) NOT NULL,
    piso INT NOT NULL,
    estado ENUM('LIBRE','OCUPADO','INACTIVO') DEFAULT 'LIBRE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    
);

CREATE TABLE departamento_propietario (
    id_departamento_propietario INT AUTO_INCREMENT PRIMARY KEY,
    id_departamento INT NOT NULL,
    id_propietario INT NOT NULL,
    fecha_adquisicion DATE NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_dep_prop_departamento FOREIGN KEY (id_departamento) REFERENCES departamento(id_departamento),
    CONSTRAINT fk_dep_prop_usuario FOREIGN KEY (id_propietario) REFERENCES usuario(id_usuario)
);

CREATE TABLE cuota_mensual (
    id_cuota_mensual INT AUTO_INCREMENT PRIMARY KEY,
    id_departamento INT NOT NULL,
    monto_base DECIMAL(10,2) NOT NULL,
    monto_mora DECIMAL(10,2) DEFAULT 0,
    monto_total DECIMAL(10,2) NOT NULL,
    fecha_emision DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    estado ENUM('PENDIENTE','PAGADO','VENCIDO') DEFAULT 'PENDIENTE',
    CONSTRAINT fk_cuota_departamento FOREIGN KEY (id_departamento) REFERENCES departamento(id_departamento)
);

CREATE TABLE pago_mantenimiento (
    id_pago_mantenimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_cuota_mensual INT NOT NULL,
    id_usuario INT NOT NULL,
    metodo_pago VARCHAR(30) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) NOT NULL DEFAULT 'REGISTRADO',
    CONSTRAINT uk_pago_cuota UNIQUE (id_cuota_mensual),
    CONSTRAINT fk_pago_cuota FOREIGN KEY (id_cuota_mensual) REFERENCES cuota_mensual(id_cuota_mensual),
    CONSTRAINT fk_pago_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT chk_pago_metodo_pago
        CHECK (
            metodo_pago IN (
                'EFECTIVO',
                'TRANSFERENCIA',
                'YAPE',
                'PLIN'
            )
        ),
    CONSTRAINT chk_pago_estado
        CHECK (
            estado IN (
                'REGISTRADO',
                'ANULADO'
            )
        )
);

INSERT INTO rol(nombre_rol) VALUES
('ADMINISTRADOR'),
('PROPIETARIO'),
('INQUILINO'),
('VIGILANTE');

INSERT INTO usuario(id_rol,nombre,apellido,dni,telefono,correo,password_hash) VALUES
(1,'Jean','Angoma','71234567','999111222','admin@condominio.com','123456'),
(2,'Carlos','Perez','74561234','988777666','carlos@correo.com','123456'),
(2,'Maria','Torres','76543210','977555444','maria@correo.com','123456'),
(3,'Ana','Lopez','70112233','955444333','ana@correo.com','123456'),
(3,'Pedro','Castillo','70998877','944333222','pedro@correo.com','123456'),
(4,'Luis','Ramirez','73322111','944888777','vigilante@correo.com','123456');

INSERT INTO trabajador(id_usuario,cargo,turno,estado) VALUES
(6,'VIGILANTE','NOCHE',TRUE);

INSERT INTO reserva(id_usuario,fecha_reserva,hora_inicio,hora_fin,estado) VALUES
(2,CURDATE() + INTERVAL 1 DAY,'10:00:00','12:00:00','APROBADA'),
(4,CURDATE() + INTERVAL 1 DAY,'11:00:00','13:00:00','PENDIENTE'),
(3,CURDATE() + INTERVAL 2 DAY,'18:00:00','21:00:00','PENDIENTE'),
(5,CURDATE() + INTERVAL 3 DAY,'12:00:00','14:00:00','RECHAZADA');

-- Torre C = id 3, Torre D = id 4, Torre E = id 5
INSERT INTO departamento (numero, piso, estado) VALUES
('301',3,'OCUPADO'),('302',3,'OCUPADO'),('303',3,'OCUPADO'),('304',3,'OCUPADO'),
('401',4,'OCUPADO'),('402',4,'OCUPADO'),('403',4,'OCUPADO'),('404',4,'OCUPADO'),
('501',5,'OCUPADO'),('502',5,'OCUPADO'),
('101',1,'OCUPADO'),('102',1,'OCUPADO'),('103',1,'OCUPADO'),('104',1,'OCUPADO'),
('201',2,'OCUPADO'),('202',2,'OCUPADO'),('203',2,'OCUPADO'),('204',2,'OCUPADO'),
('301',3,'OCUPADO'),('302',3,'OCUPADO'),
('101',1,'OCUPADO'),('102',1,'OCUPADO'),('103',1,'OCUPADO'),('104',1,'OCUPADO'),
('201',2,'OCUPADO'),('202',2,'OCUPADO'),('203',2,'OCUPADO'),('204',2,'OCUPADO'),
('301',3,'OCUPADO'),('302',3,'OCUPADO'),('303',3,'OCUPADO'),('304',3,'OCUPADO'),
('401',4,'OCUPADO'),('402',4,'OCUPADO'),('403',4,'OCUPADO'),('404',4,'OCUPADO'),
('501',5,'OCUPADO'),('502',5,'OCUPADO'),('503',5,'OCUPADO'),('504',5,'OCUPADO');

INSERT INTO departamento_propietario(id_departamento,id_propietario,fecha_adquisicion,estado) VALUES
(1,2,'2025-01-10',TRUE),
(2,2,'2025-02-12',TRUE),
(3,3,'2025-03-15',TRUE),
(4,3,'2025-04-20',TRUE);

INSERT INTO cuota_mensual
(id_departamento,monto_base,monto_mora,monto_total,fecha_emision,fecha_vencimiento,estado) VALUES
(1,250.00,0.00,250.00,'2026-06-01','2026-06-15','PENDIENTE'),
(2,250.00,20.00,270.00,'2026-06-01','2026-06-15','VENCIDO'),
(3,300.00,0.00,300.00,'2026-06-01','2026-06-15','PAGADO'),
(4,280.00,0.00,280.00,'2026-06-01','2026-06-15','PENDIENTE');

INSERT INTO pago_mantenimiento
(
    id_cuota_mensual,
    id_usuario,
    metodo_pago,
    monto,
    estado
)
VALUES
(
    3,
    3,
    'TRANSFERENCIA',
    300.00,
    'REGISTRADO'
);






