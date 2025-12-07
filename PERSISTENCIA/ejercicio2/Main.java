import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ArchivoTrabajador archivo = new ArchivoTrabajador();

        // a) Crear el archivo (Si no existe, lo inicializa como JSON vacío)
        archivo.crearArchivo();
        
        System.out.println("\n--- B) Guardar Trabajadores ---");
        Trabajador t1 = new Trabajador("Ana", 101, 3000.0);
        Trabajador t2 = new Trabajador("Beto", 102, 5000.0);
        Trabajador t3 = new Trabajador("Carlos", 103, 4000.0);

        archivo.guardarTrabajador(t1);
        archivo.guardarTrabajador(t2);
        archivo.guardarTrabajador(t3);

        System.out.println("\n--- C) Aumentar Salario ---");

        Trabajador tAumento = new Trabajador("", 101, 0.0); 
        archivo.aumentaSalario(500, tAumento); // Aumenta 500 a Ana (carnet 101)

        System.out.println("\n--- D) Buscar el de Mayor Salario ---");
        Optional<Trabajador> mayorSalario = archivo.buscarMayorSalario();
        mayorSalario.ifPresent(t -> System.out.println("Mayor Salario: " + t));

        System.out.println("\n--- E) Ordenar por Salario ---");
        List<Trabajador> ordenados = archivo.ordenarPorSalario();
        ordenados.forEach(System.out::println);
    }
}
