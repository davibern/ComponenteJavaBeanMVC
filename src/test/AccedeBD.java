package test;

import alumno.AlumnoBean;
import alumno.AlumnoBean.BDModificadaListener;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class AccedeBD implements BDModificadaListener {
    
    private AlumnoBean alumnos;
    
    public AccedeBD() {
        this.alumnos = new AlumnoBean();
        this.alumnos.addBDModificadaListener((BDModificadaListener) this);
    }
    
    public void listado() {
        
        for(int i = 0; i < this.alumnos.size() ; i++)
        {
            alumnos.seleccionarFilas(i);
            System.out.println("Alumno " + i + "\n\tDNI:" + alumnos.getDni());
            System.out.println("\tNombre: " + alumnos.getNombre());
            System.out.println("\tApellidos: " + alumnos.getApellidos());
            System.out.println("\tDireccion: " + alumnos.getDireccion());
            System.out.println("\tFecha de nacimiento: " + alumnos.getFechaNacimiento());
        }
    }

    void anade() {
        
        alumnos.setDni("41526277W");
        alumnos.setNombre("Angelica");
        alumnos.setApellidos("Swartz Lacaster");
        alumnos.setDireccion("C/ Simon, nº 97");
        alumnos.setFechaNacimiento(Date.valueOf("1982-03-13"));
        try {
            alumnos.addAlumno();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccedeBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void capturarBDModificada(AlumnoBean.BDModificadaEvent ev) {
        System.out.println("Se ha añadido un elemento a la base de datos");
    }
    
}
