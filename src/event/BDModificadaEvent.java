package event;

import java.util.EventObject;

/**
 * Código para añadir un nuevo alumno a la base de datos.
 * Cada vez que se modifica el estado de la BD se genera un evento para 
 * que se recargue el componente.
 * 
 * @author davibern
 */
public class BDModificadaEvent extends EventObject {

    public BDModificadaEvent(Object o) {
        super(o);
    }

}
