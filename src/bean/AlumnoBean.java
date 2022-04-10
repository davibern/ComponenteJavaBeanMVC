package bean;

import entity.Alumno;
import event.BDModificadaEvent;
import java.beans.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davibern
 */
public class AlumnoBean implements Serializable {
    
    private PropertyChangeSupport propertySupport;
    protected String dni;
    protected String nombre;
    protected String apellidos;
    protected String direccion;
    protected Date fechaNacimiento;
    // Se usará un vector auxiliar para cargar la información de la tabla de forma que tengamos acceso
    // a los datos sin necesidad de estar conectados constántemente
    private Vector alumnos = new Vector();
    // Gestión de eventos
    private BDModificadaListener receptor;
    
    public AlumnoBean() {
        propertySupport = new PropertyChangeSupport(this);
        
        try {
            recargarFilas();
        } catch (ClassNotFoundException e) {
            this.dni = "";
            this.nombre = "";
            this.apellidos = "";
            this.direccion = "";
            Logger.getLogger(AlumnoBean.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }

    public PropertyChangeSupport getPropertySupport() {
        return propertySupport;
    }

    public void setPropertySupport(PropertyChangeSupport propertySupport) {
        this.propertySupport = propertySupport;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    /**
     * Método que añade un alumno a la base de datos y añade
     * un registro a ésta formado a partir de los valores de las 
     * propiedades del componente.
     * 
     * Se presupone que se han usado los métodos set para configurar
     * adecuadamente las propiedades con los dato sdel nuevo registro.
     * 
     * @throws ClassNotFoundException 
     */
    public void addAlumno() throws ClassNotFoundException {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/alumnos?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            PreparedStatement s = con.prepareStatement("insert into alumnos values (?, ?, ?, ?, ?)");
            
            s.setString(1, this.dni);
            s.setString(2, this.nombre);
            s.setString(3, this.apellidos);
            s.setString(4, this.direccion);
            s.setDate(5, (java.sql.Date) this.fechaNacimiento);
            
            s.executeUpdate();
            recargarFilas();
            receptor.capturarBDModificada(new BDModificadaEvent(this));
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Actualiza el contenido de la tabla en el vector de alumnos.
     * Las propiedades contienen el valor del primer elemento de la tabla
     * @throws ClassNotFoundException Clase no detectada
     */
    private void recargarFilas() throws ClassNotFoundException {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/alumnos?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from alumnos");
            
            while (rs.next()) {
                Alumno a = new Alumno(
                        rs.getString("DNI"),
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getString("Direccion"),
                        rs.getDate("FechaNac"));
                alumnos.add(a);
            }
            
            Alumno a = new Alumno();
            a = (Alumno) alumnos.elementAt(1);
            this.dni = a.dni;
            this.nombre = a.nombre;
            this.direccion = a.direccion;
            this.fechaNacimiento = a.fechaNacimiento;
            
            rs.close();
            con.close();
        } catch (SQLException e) {
            this.dni = "";
            this.nombre = "";
            this.apellidos = "";
            this.direccion = "";
            Logger.getLogger(AlumnoBean.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    /**
     * Selecciona una fila para cargar en el componente
     * @param i número de la fila a cargar en las propiedades del componente
     */
    public void seleccionarFilas(int i ) {
        if (i <= alumnos.size()) {
            Alumno a = new Alumno();
            a = (Alumno) alumnos.elementAt(i);
            this.dni = a.dni;
            this.nombre = a.nombre;
            this.apellidos = a.apellidos;
            this.direccion = a.direccion;
            this.fechaNacimiento = a.fechaNacimiento;
        } else {
            this.dni = "";
            this.nombre = "";
            this.apellidos = "";
            this.direccion = "";
        }
    }
    
    /**
     * Selecciona una fila para cargar en el componente
     * @param dni dni a cargar en las propiedades del componente
     */
    public void seleccionarDNI(String dni) {
        Alumno a = new Alumno();
        int i = 0;
        
        this.dni = "";
        this.nombre = "";
        this.apellidos = "";
        this.direccion = "";
        
        while (this.dni.equals("") && i<= alumnos.size()) {
            a = (Alumno) alumnos.elementAt(i);
            if (a.dni.equals(dni)){
                this.dni = a.dni;
                this.nombre = a.dni;
                this.apellidos = a.apellidos;
                this.direccion = a.direccion;
                this.fechaNacimiento = a.fechaNacimiento;
            }
        }
        
    }
    
    public int size() {
        return this.alumnos.size();
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertySupport.removePropertyChangeListener(listener);
    }
    
    /**
     * Define la interfaz para el nuevo tipo de evento
     */
    public interface BDModificadaListener extends EventListener {
        public void capturarBDModificada(BDModificadaEvent ev);
    }
    
    /**
     * Añade el evento al componente
     * @param receptor 
     */
    public void addBDModificadaListener(BDModificadaListener receptor) {
        this.receptor  = receptor;
    }
    
    public void removeBDModificadaListener(BDModificadaListener recpetor) {
        this.receptor = null;
    }
    
}
