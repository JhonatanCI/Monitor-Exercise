import java.util.Random;
import java.util.concurrent.Semaphore;

public class Monitor  extends Thread {

    private Corredor corredor;

    public Monitor(Corredor corredor) {
        this.corredor = corredor;
    }

    private Semaphore monitorSemaphore = new Semaphore(0);

    public void run() {
        while (true) {
            try {
                System.out.println("Monitor durmiendo...");
                monitorSemaphore.acquire();
                System.out.println("¡El monitor ha sido despertado!");
                
                Estudiante siguiente = corredor.obtenerSiguiente();
                siguiente = corredor.cola.poll();
                if (siguiente!=null) {
                    atender(siguiente);
                }

                while (corredor.obtenerSiguiente()!=null) {
                    siguiente = corredor.cola.poll();
                    atender(siguiente);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

       public void solicitarAyuda(Estudiante estudiante) {

        if(monitorSemaphore.availablePermits()==0){
           
            corredor.cola.add(estudiante);   
            monitorSemaphore.release();
            
       }else if (corredor.cola.size()<2) {
            System.out.println("Estudiante " + estudiante.id + " se sienta en el corredor.");
            corredor.cola.add(estudiante);
            try {
                corredor.sillas.acquire();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        } else {
            System.out.println("Estudiante " + estudiante.id + " se va a programar a sala de cómputo y regresa más tarde.");
            estudiante.estudiSemaphore.release();
        }

       
    }

    public synchronized void atender(Estudiante estudiante) {
        System.out.println("Monitor atiende al estudiante " + estudiante.id);
        try {
            Thread.sleep(new Random().nextInt(5000)); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Monitor termina de ayudar al estudiante " + estudiante.id);
        estudiante.setAtendido(true);
    }
}
