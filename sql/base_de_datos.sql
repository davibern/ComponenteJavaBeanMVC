use alumnos;

CREATE TABLE IF NOT EXISTS `alumnos` ( 
    `DNI` varchar(9) NOT NULL, 
    `Nombre` varchar(50) NOT NULL, 
    `Apellidos` varchar(70) NOT NULL, 
    `Direccion` varchar(100) NOT NULL, 
    `FechaNac` date NOT NULL, 
    PRIMARY KEY (`DNI`) 
) ENGINE=MyISAM DEFAULT CHARSET=latin1; 

INSERT INTO `alumnos` (`DNI`, `Nombre`, `Apellidos`, `Direccion`, `FechaNac`) VALUES 
('12345678A', 'José Alberto', 'González Pérez', 'C/Albahaca, nº14, 1ºD', '1986-07-15'), 
('23456789B', 'Almudena', 'Cantero Verdemar', 'Avd/ Profesor Alvarado, n27, 8ºA', '1988-11-04'), 
('14785236d', 'Martín', 'Díaz Jiménez', 'C/Luis de Gongora, nº2.', '1987-03-09'), 
('96385274f', 'Lucas', 'Buendia Portes', 'C/Pintor Sorolla, nº 16, 4ºB', '1988-07-10');