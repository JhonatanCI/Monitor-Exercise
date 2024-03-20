import java.util.Random;
import java.util.concurrent.Semaphore;

public class Estudiante extends Thread {

    public int id;
    private Monitor monitor;
    private Corredor corredor;
    public boolean atendido = false;
    public Semaphore estudiSemaphore = new Semaphore(0);

    public Estudiante(int id, Monitor monitor,Corredor corredor) {
        this.id = id;
        this.monitor = monitor;
        this.corredor = corredor;
    }

    public void run() {
        while (!atendido) {
            programar();
            System.out.println("El estudiante "+id+" pide ayuda");
            monitor.solicitarAyuda(this);
            try {
                estudiSemaphore.acquire();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }  
    }
    

    public void programar() {
        // Simular tiempo de programación
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setAtendido(boolean atendido){
        this.atendido = atendido;
    }
}