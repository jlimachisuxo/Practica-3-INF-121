import java.util.Arrays;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        
        String nombreArchivo = "datos_notas.json";
        
        // 1. Inicializar ArchiNota. Intentará cargar datos existentes.
        ArchiNota archivoNotas = new ArchiNota(nombreArchivo);

        // 2. Crear datos de prueba (Estudiantes y Notas)
        Estudiante e1 = new Estudiante("1001", "Ana", "García", "López", 20);
        Estudiante e2 = new Estudiante("1002", "Juan", "Pérez", "Mendoza", 25);
        Estudiante e3 = new Estudiante("1003", "María", "Soliz", "Vargas", 19);
        Estudiante e4 = new Estudiante("1004", "Pedro", "Mamani", "Quispe", 22);

        List<Nota> nuevasNotas = Arrays.asList(
            new Nota("PROGRAMACION", 85.5, e1),
            new Nota("PROGRAMACION", 92.0, e2),
            new Nota("BDD", 78.0, e1),
            new Nota("BDD", 92.0, e3), // Mismas notas que e2 para prueba de "mejores notas"
            new Nota("ESTADISTICA", 60.0, e4)
        );

        // --- Ejecución de los métodos ---

        // b) Agregar notas (estudiantes)
        System.out.println("\n--- b) Agregar Notas ---");
        archivoNotas.agregarNotas(nuevasNotas);

        // Mostrar todas las notas después de agregar
        System.out.println("\n--- Lista Actual de Notas ---");
        archivoNotas.getNotas().forEach(System.out::println);
        
        // c) Obtener el promedio de notas
        System.out.println("\n--- c) Promedio de Notas ---");
        double promedio = archivoNotas.obtenerPromedioNotas();
        System.out.printf("El promedio de todas las notas es: %.2f\n", promedio);

        // d) Buscar al o los estudiantes con la mejor nota
        System.out.println("\n--- d) Estudiante(s) con la Mejor Nota ---");
        List<Nota> mejoresNotas = archivoNotas.buscarMejorNota();
        mejoresNotas.forEach(nota -> 
            System.out.println("Mejor Nota: " + nota.getNotaFinal() + " por " + nota.getEstudiante().getNombre() + " en " + nota.getMateria())
        );

        // e) Eliminar a todos los estudiantes de una determinada materia
        System.out.println("\n--- e) Eliminar Notas por Materia (BDD) ---");
        String materiaAEliminar = "BDD";
        int eliminados = archivoNotas.eliminarNotasPorMateria(materiaAEliminar);
        System.out.println("Se eliminaron " + eliminados + " notas de la materia: " + materiaAEliminar);

        // Mostrar todas las notas después de eliminar
        System.out.println("\n--- Lista Final de Notas ---");
        archivoNotas.getNotas().forEach(System.out::println);

    }
}
