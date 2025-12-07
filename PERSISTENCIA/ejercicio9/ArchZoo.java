import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArchZoo {
    private String nombre; 
    private Gson gson;

    public ArchZoo(String nombre) {
        this.nombre = nombre;

        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Método privado para leer todos los zoológicos del archivo
    private List<Zoologico> leerZoologicos() {
        try (Reader reader = new FileReader(nombre)) {
            // Define el tipo de la lista para que Gson pueda deserializar correctamente
            Type listType = new TypeToken<ArrayList<Zoologico>>(){}.getType();
            List<Zoologico> zoológicos = gson.fromJson(reader, listType);
            return zoológicos != null ? zoológicos : new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + nombre + " no encontrado. Creando uno nuevo.");
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void escribirZoologicos(List<Zoologico> zoológicos) {
        try (Writer writer = new FileWriter(nombre)) {
            gson.toJson(zoológicos, writer);
            System.out.println("Datos guardados en " + nombre + " exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- a) Implementar los métodos crear, modificar y eliminar ---

    public void crear(Zoologico nuevoZoo) {
        List<Zoologico> zoológicos = leerZoologicos();
        // Evitar duplicados por ID (aunque el problema no lo exige, es buena práctica)
        boolean existe = zoológicos.stream().anyMatch(z -> z.getId() == nuevoZoo.getId());
        if (!existe) {
            zoológicos.add(nuevoZoo);
            escribirZoologicos(zoológicos);
            System.out.println("Zoológico Creado: " + nuevoZoo.getNombre());
        } else {
            System.out.println("Error: Ya existe un zoológico con el ID " + nuevoZoo.getId());
        }
    }

    /**
     * Modifica un zoológico existente buscando por ID.
     */
    public void modificar(Zoologico zooModificado) {
        List<Zoologico> zoológicos = leerZoologicos();
        boolean modificado = false;

        for (int i = 0; i < zoológicos.size(); i++) {
            if (zoológicos.get(i).getId() == zooModificado.getId()) {
         
                zoológicos.set(i, zooModificado);
                modificado = true;
                break;
            }
        }

        if (modificado) {
            escribirZoologicos(zoológicos);
            System.out.println("Zoológico Modificado: " + zooModificado.getNombre());
        } else {
            System.out.println("Error: No se encontró zoológico con el ID " + zooModificado.getId() + " para modificar.");
        }
    }

    /**
     * Elimina un zoológico buscando por ID.
     */
    public void eliminar(int idZoo) {
        List<Zoologico> zoológicos = leerZoologicos();
        // Usar removeIf para eliminar el zoológico cuyo ID coincida
        boolean eliminado = zoológicos.removeIf(z -> z.getId() == idZoo);

        if (eliminado) {
            escribirZoologicos(zoológicos);
            System.out.println("Zoológico Eliminado con ID: " + idZoo);
        } else {
            System.out.println("Error: No se encontró zoológico con el ID " + idZoo + " para eliminar.");
        }
    }

    // --- b) Listar los zoológicos que contengan mayor cantidad variedad de animales ---

    public void listarMayorVariedad() {
        List<Zoologico> zoológicos = leerZoologicos();
        if (zoológicos.isEmpty()) {
            System.out.println("No hay zoológicos para listar.");
            return;
        }

        int maxVariedades = zoológicos.stream()
                .max(Comparator.comparingInt(Zoologico::getNroAnimales))
                .get().getNroAnimales();

        System.out.println("\n*** Zoológicos con Mayor Variedad de Animales (" + maxVariedades + " variedades) ***");

        // Filtrar y mostrar los zoológicos que igualan el máximo
        zoológicos.stream()
                .filter(z -> z.getNroAnimales() == maxVariedades)
                .forEach(z -> System.out.println("  " + z));
    }

    // --- c) Listar los zoológicos vacíos y eliminarlos ---
    public void listarYEliminarVacios() {
        List<Zoologico> zoológicos = leerZoologicos();

        // 1. Listar los vacíos
        List<Zoologico> vacios = zoológicos.stream()
                .filter(z -> z.getNroAnimales() == 0)
                .collect(Collectors.toList());

        if (vacios.isEmpty()) {
            System.out.println("\n*** No hay zoológicos vacíos para eliminar. ***");
            return;
        }

        System.out.println("\n*** Zoológicos Vacíos Encontrados y Eliminados: ***");
        vacios.forEach(z -> System.out.println("  " + z));

        // 2. Eliminar los vacíos de la lista principal
        zoológicos.removeAll(vacios);

        // 3. Persistir la lista modificada
        escribirZoologicos(zoológicos);
        System.out.println("Eliminación completada.");
    }

    // --- d) Mostrar a los animales de la especie x ---

    public void mostrarAnimalesPorEspecie(String especie) {
        List<Zoologico> zoológicos = leerZoologicos();

        System.out.println("\n*** Animales de la Especie: " + especie + " ***");
        boolean encontrado = false;

        for (Zoologico zoo : zoológicos) {
            List<Animal> animalesDeEspecie = zoo.getAnimales().stream()
                    .filter(a -> a.getEspecie().equalsIgnoreCase(especie))
                    .collect(Collectors.toList());

            if (!animalesDeEspecie.isEmpty()) {
                System.out.println("-> Zoológico: " + zoo.getNombre());
                animalesDeEspecie.forEach(System.out::println);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron animales de la especie " + especie + ".");
        }
    }

    // --- e) Mover los animales de un zoológico x a un zoológico y ---

    public void moverAnimales(int idOrigen, int idDestino) {
        List<Zoologico> zoológicos = leerZoologicos();

        // Buscar los zoológicos
        Zoologico zooOrigen = zoológicos.stream().filter(z -> z.getId() == idOrigen).findFirst().orElse(null);
        Zoologico zooDestino = zoológicos.stream().filter(z -> z.getId() == idDestino).findFirst().orElse(null);

        if (zooOrigen == null || zooDestino == null) {
            System.out.println("\nError: Uno o ambos zoológicos no existen.");
            return;
        }

        if (zooOrigen.getId() == zooDestino.getId()) {
            System.out.println("\nError: El zoológico de origen y destino son el mismo.");
            return;
        }

        // Mover los animales:
        // 1. Agregar todos los animales del origen al destino
        zooDestino.getAnimales().addAll(zooOrigen.getAnimales());

        // 2. Limpiar la lista de animales del origen
        zooOrigen.getAnimales().clear();

        // 3. Actualizar el nroAnimales (tamaño de la lista) en ambos.
        zooDestino.getNroAnimales();
        zooOrigen.getNroAnimales();

        // 4. Persistir los cambios (ya que las referencias en la lista `zoológicos` se modificaron)
        escribirZoologicos(zoológicos);
        System.out.println("\n*** Movimiento Completado ***");
        System.out.println("Se movieron " + zooDestino.getNroAnimales() + " variedades de animales.");
        System.out.println("Origen (" + zooOrigen.getNombre() + "): " + zooOrigen.getNroAnimales() + " variedades restantes.");
        System.out.println("Destino (" + zooDestino.getNombre() + "): " + zooDestino.getNroAnimales() + " variedades totales.");
    }
    
    // Método para mostrar el contenido actual
    public void mostrarContenido() {
        List<Zoologico> zoológicos = leerZoologicos();
        System.out.println("\n--- Contenido Actual del Archivo " + nombre + " ---");
        if (zoológicos.isEmpty()) {
            System.out.println("El archivo está vacío.");
            return;
        }
        for (Zoologico z : zoológicos) {
            System.out.println("  " + z);
            if (!z.getAnimales().isEmpty()) {
                 System.out.println("    Animales:");
                 z.getAnimales().forEach(System.out::println);
            }
        }
        System.out.println("---------------------------------------------------");
    }
}