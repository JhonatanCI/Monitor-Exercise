public class Main {
    public static void main(String[] args) {
        Corredor corredor = new Corredor();
        Monitor monitor = new Monitor(corredor);
        monitor.start(); 

        for (int i = 1; i <= 5; i++) {
            Estudiante estudiante = new Estudiante(i, monitor,corredor);
            estudiante.start();
        }
    }
}