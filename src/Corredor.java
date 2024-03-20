import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Corredor {
    Semaphore sillas = new Semaphore(3); 
    Queue<Estudiante> cola = new LinkedList<>(); 

    public Estudiante obtenerSiguiente() {
        return cola.peek(); 
    }
}
