package test;

/**
 *
 * @author davibern
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AccedeBD gestion = new AccedeBD();

        gestion.listado();
        gestion.anade();
    }
}
