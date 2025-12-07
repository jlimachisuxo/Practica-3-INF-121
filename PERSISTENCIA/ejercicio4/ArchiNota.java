import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArchiNota {
    private String nombreArchi;
    private List<Nota> notas; 

    // Constructor
    public ArchiNota(String nombreArchi) {
        this.nombreArchi = nombreArchi;
        this.notas = new ArrayList<>();

        cargarDatos();
    }

    public void guardarDatos() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(nombreArchi)) {
            gson.toJson(notas, writer);
            System.out.println("Datos guardados en " + nombreArchi);
        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
    }

    private void cargarDatos() {
        try (Reader reader = new FileReader(nombreArchi)) {
            Gson gson = new Gson();
           
            Type listType = new TypeToken<ArrayList<Nota>>(){}.getType();
            this.notas = gson.fromJson(reader, listType);
            if (this.notas == null) {
                this.notas = new ArrayList<>();
            }
            System.out.println("Datos cargados desde " + nombreArchi);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo " + nombreArchi + " no encontrado. Se crea una nueva lista.");
        } catch (IOException e) {
            System.err.println("Error de I/O al cargar datos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al deserializar datos: " + e.getMessage());
        }
    }
    /**
     * b) Implementar un método para agregar a varios estudiantes (notas).
     */
    public void agregarNotas(List<Nota> nuevasNotas) {
        this.notas.addAll(nuevasNotas);
        guardarDatos(); // Guardar después de la modificación
        System.out.println("Se han agregado " + nuevasNotas.size() + " notas.");
    }

    /**
     * c) Obtener el promedio de notas de todos los estudiantes.
     */
    public double obtenerPromedioNotas() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double sumaNotas = notas.stream()
            .mapToDouble(Nota::getNotaFinal)
            .sum();
        return sumaNotas / notas.size();
    }

    /**
     * d) Buscar al o los estudiantes con la mejor nota.
     */
    public List<Nota> buscarMejorNota() {
        if (notas.isEmpty()) {
            return new ArrayList<>();
        }

        // 1. Encontrar la nota final máxima
        double notaMaxima = notas.stream()
            .mapToDouble(Nota::getNotaFinal)
            .max()
            .orElse(0.0); 

        // 2. Filtrar todas las notas que coincidan con esa nota máxima
        return notas.stream()
            .filter(nota -> nota.getNotaFinal() == notaMaxima)
            .collect(Collectors.toList());
    }

    /**
     * e) Eliminar a todos los estudiantes de una determinada materia.
     */
    public int eliminarNotasPorMateria(String materia) {
        int tamanoInicial = notas.size();

        notas.removeIf(nota -> nota.getMateria().equalsIgnoreCase(materia));
        
        int eliminados = tamanoInicial - notas.size();
        
        if (eliminados > 0) {
            guardarDatos(); 
        }
        return eliminados;
    }

    public List<Nota> getNotas() {
        return notas;
    }
}