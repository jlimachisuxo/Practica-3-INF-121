import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArchivoCharango {
    private final String ARCHIVO_NOMBRE = "charangos.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    /**
     * Guarda la lista de Charangos en un archivo JSON.
     */
    public void guardarCharangos(List<Charango> charangos) {
        try (FileWriter writer = new FileWriter(ARCHIVO_NOMBRE)) {
            // Serializa la lista a formato JSON
            gson.toJson(charangos, writer); 
            System.out.println("Charangos guardados en " + ARCHIVO_NOMBRE);
        } catch (IOException e) {
            System.err.println("Error al guardar los charangos: " + e.getMessage());
        }
    }

    public List<Charango> cargarCharangos() {
        try (FileReader reader = new FileReader(ARCHIVO_NOMBRE)) {
            // Define el tipo de objeto que esperamos (una lista de Charango)
            Type tipoListaCharango = new TypeToken<ArrayList<Charango>>() {}.getType();
            // Deserializa el JSON a la lista
            List<Charango> charangos = gson.fromJson(reader, tipoListaCharango); 
            System.out.println("Charangos cargados desde " + ARCHIVO_NOMBRE);
            // Retorna una lista vacía si el archivo estaba vacío o nulo
            return charangos != null ? charangos : new ArrayList<>(); 
        } catch (IOException e) {
            System.err.println("Archivo " + ARCHIVO_NOMBRE + " no encontrado o error de lectura. Creando lista vacía.");
            return new ArrayList<>();
        }
    }

    // b) Eliminar a los charangos cuyas cuerdas en estado false sea mayor a 6.
    public List<Charango> eliminarCharangosPorCuerdas(List<Charango> charangos) {
        // Usamos un Stream para filtrar y excluir los elementos que cumplan la condición.
        List<Charango> charangosFiltrados = charangos.stream()
            .filter(charango -> {
                int cuerdasRotas = 0;
                for (boolean cuerda : charango.getCuerdas()) {
                    if (!cuerda) { // false = cuerda "rota" o no puesta
                        cuerdasRotas++;
                    }
                }
                return cuerdasRotas <= 6; 
            })
            .collect(Collectors.toList());
        return charangosFiltrados;
    }

    // c) Listar a los charangos de material x.
    public List<Charango> listarCharangosPorMaterial(List<Charango> charangos, String materialBuscado) {
        return charangos.stream()
            .filter(c -> c.getMaterial().equalsIgnoreCase(materialBuscado))
            .collect(Collectors.toList());
    }

    // d) Buscar los charangos con 10 cuerdas.
    public List<Charango> buscarCharangosPorNroCuerdas(List<Charango> charangos, int nroCuerdasBuscado) {
        // Asumiendo que se refiere al atributo 'nroCuerdas', no al tamaño del array 'cuerdas'.
        return charangos.stream()
            .filter(c -> c.getNroCuerdas() == nroCuerdasBuscado)
            .collect(Collectors.toList());
    }

    // e) Ordenar los charangos por material en orden alfabético.
    public void ordenarCharangosPorMaterial(List<Charango> charangos) {
        Collections.sort(charangos, Comparator.comparing(Charango::getMaterial));
    }
}
