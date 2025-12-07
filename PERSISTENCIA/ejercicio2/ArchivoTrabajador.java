import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ArchivoTrabajador {

    private String nombreArch; 
    
    private Gson gson;

    private static final Type TRABAJADOR_LIST_TYPE = new TypeToken<ArrayList<Trabajador>>() {}.getType();

    // + ArchivoTrabajador()
    public ArchivoTrabajador() {
        this.nombreArch = "trabajadores.json"; 
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    private List<Trabajador> cargarTrabajadores() {
        File archivo = new File(nombreArch);
        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(nombreArch)) {
            // Deserializa el JSON a una lista de Trabajador
            List<Trabajador> lista = gson.fromJson(reader, TRABAJADOR_LIST_TYPE);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void guardarLista(List<Trabajador> trabajadores) {
        try (Writer writer = new FileWriter(nombreArch)) {
            // Serializa la lista a JSON y escribe en el archivo
            gson.toJson(trabajadores, writer);
            System.out.println("Archivo guardado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public void crearArchivo() {
        List<Trabajador> trabajadores = cargarTrabajadores();
        guardarLista(trabajadores);
    }
    
    public void guardarTrabajador(Trabajador t) {
        List<Trabajador> trabajadores = cargarTrabajadores();
        boolean existe = trabajadores.stream()
            .anyMatch(trab -> trab.getCarnet() == t.getCarnet());

        if (existe) {
            System.out.println("Error: Ya existe un trabajador con el carnet " + t.getCarnet());
            return;
        }

        trabajadores.add(t);
        guardarLista(trabajadores);
    }

    public void aumentaSalario(int aumento, Trabajador t) {
        List<Trabajador> trabajadores = cargarTrabajadores();

        Optional<Trabajador> trabajadorOpt = trabajadores.stream()
            .filter(trab -> trab.getCarnet() == t.getCarnet())
            .findFirst();

        if (trabajadorOpt.isPresent()) {
            Trabajador trabajadorEncontrado = trabajadorOpt.get();
            double nuevoSalario = trabajadorEncontrado.getSalario() + aumento;
            trabajadorEncontrado.setSalario(nuevoSalario);
            guardarLista(trabajadores);
            System.out.println("Salario de " + trabajadorEncontrado.getNombre() + 
                               " actualizado a " + nuevoSalario);
        } else {
            System.out.println("Error: Trabajador con carnet " + t.getCarnet() + " no encontrado.");
        }
    }

    /**
     * d) Buscar el trabajador con el mayor salario.
     */
    public Optional<Trabajador> buscarMayorSalario() {
        List<Trabajador> trabajadores = cargarTrabajadores();
        
        if (trabajadores.isEmpty()) {
            System.out.println("La lista de trabajadores está vacía.");
            return Optional.empty();
        }

        // Utiliza Streams y Comparator para encontrar el máximo
        return trabajadores.stream()
            .max(Comparator.comparingDouble(Trabajador::getSalario));
    }

    /**
     * e) Ordenar a los trabajadores por su salario.
     */
    public List<Trabajador> ordenarPorSalario() {
        List<Trabajador> trabajadores = cargarTrabajadores();
        
        if (trabajadores.isEmpty()) {
            System.out.println("La lista de trabajadores está vacía.");
            return trabajadores;
        }

        Collections.sort(trabajadores, Comparator.comparingDouble(Trabajador::getSalario));

        guardarLista(trabajadores);

        return trabajadores;
    }
}