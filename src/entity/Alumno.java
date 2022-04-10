package entity;

import java.util.Date;

/**
 *
 * @author davibern
 */
public class Alumno {
    
    // Atributos
    public String dni;
    public String nombre;
    public String apellidos;
    public String direccion;
    public Date fechaNacimiento;

    /**
     * Constructor si parámetros
     */
    public Alumno() {}

    /**
     * Constructor con parámetros
     * @param dni dni del alumno
     * @param nombre nombre del alumno
     * @param apellidos apellidos del alumno
     * @param direccion dirección completa del alumno
     * @param fechaNacimiento fecha de nacimiento
     */
    public Alumno (String dni, String nombre, String apellidos, String direccion, Date fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }
}
