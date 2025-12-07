import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArchRefri {

    private String nombre; 
    private static List<Alimento> listaAlimentos = new ArrayList<>();
    private static final String FILE_PATH = "refrigerador.json"; // Nombre del archivo de persistencia
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Constructor (solo para inicializar el nombre)
    public ArchRefri(String nombre) {
        this.nombre = nombre;
    }

    public static void cargar() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            // Se usa TypeToken para deserializar List<Alimento> correctamente
            Type listType = new TypeToken<List<Alimento>>() {}.getType();
            List<Alimento> cargados = GSON.fromJson(reader, listType);
            if (cargados != null) {
                listaAlimentos = cargados;
                System.out.println("Datos cargados exitosamente desde " + FILE_PATH);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Se crea una nueva lista vacía.");
        } catch (IOException e) {
            System.err.println("Error de I/O al cargar: " + e.getMessage());
        }
    }

    /** Guarda la lista de alimentos actual en el archivo JSON **/
    public static void guardar() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            GSON.toJson(listaAlimentos, writer);
            System.out.println("Datos guardados exitosamente en " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error de I/O al guardar: " + e.getMessage());
        }
    }


    /** a) Implementar el método para Crear **/
    public static void crearAlimento(Alimento nuevoAlimento) {
        // Se añade el alimento si no existe (se usa el equals de Alimento que compara por nombre)
        if (!listaAlimentos.contains(nuevoAlimento)) {
            listaAlimentos.add(nuevoAlimento);
            System.out.println("Creado: " + nuevoAlimento.getNombre());
            guardar();
        } else {
            System.out.println("Alimento ya existe: " + nuevoAlimento.getNombre());
        }
    }

    /** a) Implementar el método para Modificar por nombre **/
    public static void modificarAlimento(String nombreBusqueda, String nuevaFecha, int nuevaCantidad) {
        Optional<Alimento> alimentoOpt = listaAlimentos.stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombreBusqueda))
                .findFirst();

        if (alimentoOpt.isPresent()) {
            Alimento alimento = alimentoOpt.get();
            alimento.setFechaVencimiento(nuevaFecha);
            alimento.setCantidad(nuevaCantidad);
            System.out.println("Modificado: " + nombreBusqueda + " -> " + alimento.toString());
            guardar();
        } else {
            System.out.println("Modificación fallida: Alimento no encontrado: " + nombreBusqueda);
        }
    }

    /** a) Implementar el método para Eliminar por nombre **/
    public static void eliminarAlimento(String nombreBusqueda) {
        boolean eliminado = listaAlimentos.removeIf(a -> a.getNombre().equalsIgnoreCase(nombreBusqueda));
        if (eliminado) {
            System.out.println("Eliminado: " + nombreBusqueda);
            guardar();
        } else {
            System.out.println("Eliminación fallida: Alimento no encontrado: " + nombreBusqueda);
        }
    }

    /** b) Mostrar los alimentos que caducaron antes de una fecha dada X **/
    public static void mostrarCaducadosAntesDe(String fechaX) {
        try {
            LocalDate fechaLimite = LocalDate.parse(fechaX, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            List<Alimento> vencidos = listaAlimentos.stream()
                    .filter(a -> {
                        LocalDate fechaVencimiento = a.getFechaComoLocalDate();
                        return fechaVencimiento != null && fechaVencimiento.isBefore(fechaLimite);
                    })
                    .collect(Collectors.toList());

            System.out.println("\nAlimentos vencidos antes de " + fechaX + ":");
            if (vencidos.isEmpty()) {
                System.out.println("  (Ninguno)");
            } else {
                vencidos.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Error en formato de fechaX. Use 'yyyy-MM-dd'.");
        }
    }

    /** c) Eliminar los alimentos que tengan cantidad 0 **/
    public static void eliminarCantidadCero() {
        long antes = listaAlimentos.size();
        boolean modificado = listaAlimentos.removeIf(a -> a.getCantidad() == 0);
        long despues = listaAlimentos.size();

        if (modificado) {
            System.out.println("\nEliminados " + (antes - despues) + " alimentos con cantidad 0.");
            guardar();
        } else {
            System.out.println("\nNo se encontraron alimentos con cantidad 0 para eliminar.");
        }
    }

    /** d) Buscar los alimentos ya vencidos (comparado con la fecha actual) **/
    public static void buscarAlimentosVencidos() {
        List<Alimento> vencidos = listaAlimentos.stream()
                .filter(Alimento::estaVencido)
                .collect(Collectors.toList());

        System.out.println("\nAlimentos ya vencidos (comparado con hoy, " + LocalDate.now() + "):");
        if (vencidos.isEmpty()) {
            System.out.println("  (Ninguno)");
        } else {
            vencidos.forEach(System.out::println);
        }
    }

    /** e) Mostrar el alimento que tenga más cantidad en el refri **/
    public static void mostrarAlimentoConMasCantidad() {
        Optional<Alimento> maxAlimento = listaAlimentos.stream()
                .max(Comparator.comparingInt(Alimento::getCantidad));

        System.out.println("\nAlimento con mayor cantidad:");
        if (maxAlimento.isPresent()) {
            System.out.println("  " + maxAlimento.get().toString());
        } else {
            System.out.println("  (La lista de alimentos está vacía)");
        }
    }

    // Método para imprimir toda la lista (útil para verificar)
    public static void mostrarTodos() {
        System.out.println("\n--- Contenido Actual del Refrigerador ---");
        if (listaAlimentos.isEmpty()) {
            System.out.println("(Vacío)");
        } else {
            listaAlimentos.forEach(System.out::println);
        }
        System.out.println("----------------------------------------");
    }
}