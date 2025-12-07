import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArchNino {

    private List<Nino> na; // Lista de Ninos, para persistencia.

    private final String RUTA_ARCHIVO = "Ninos.json";
    private final Gson gson = new Gson();

    public ArchNino() {

        this.na = new ArrayList<>();
        cargar(); 
    }

    private void cargar() {
        try (Reader reader = new FileReader(RUTA_ARCHIVO)) {
            Type tipoListaNino = new TypeToken<List<Nino>>() {}.getType();

            List<Nino> listaCargada = gson.fromJson(reader, tipoListaNino);
            if (listaCargada != null) {
                this.na = listaCargada;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de persistencia no encontrado. Creando nueva lista.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Guarda la lista de Ninos en el archivo JSON. */
    private void guardar() {
        try (Writer writer = new FileWriter(RUTA_ARCHIVO)) {
            gson.toJson(this.na, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- a) Crear, Leer, Listar, Mostrar ---

    /** Crea (agrega) un Nino y guarda la lista. */
    public void crear(Nino Nino) {
        na.add(Nino);
        guardar();
        System.out.println("Nino agregado y lista guardada.");
    }

    /** Lista todos los Ninos. */
    public List<Nino> listar() {
        return na;
    }

    /** Muestra el detalle de un Nino por CI. */
    public Nino leer(int ci) {
        for (Nino n : na) {
            if (n.getCi() == ci) {
                return n;
            }
        }
        return null;
    }

    // --- b) Cuántos Ninos tienen el peso adecuado ---
    
    public int contarNinosConPesoAdecuado(double pesoIdealMin, double pesoIdealMax, double tallaIdealMin, double tallaIdealMax) {
        int contador = 0;
        for (Nino n : na) {
            if (n.tienePesoAdecuado(pesoIdealMin, pesoIdealMax, tallaIdealMin, tallaIdealMax)) {
                contador++;
            }
        }
        return contador;
    }

    // --- c) Mostrar Ninos que NO tienen el peso o la talla adecuada ---
    public List<Nino> NinosConPesoOTallaInadecuada(double pesoIdealMin, double pesoIdealMax, double tallaIdealMin, double tallaIdealMax) {
        List<Nino> inadecuados = new ArrayList<>();
        for (Nino n : na) {
            if (!n.tienePesoAdecuado(pesoIdealMin, pesoIdealMax, tallaIdealMin, tallaIdealMax)) {
                inadecuados.add(n);
            }
        }
        return inadecuados;
    }
    
    // --- d) Determinar el promedio de edad ---
    public double promedioEdad() {
        if (na.isEmpty()) {
            return 0.0;
        }
        int sumaEdades = 0;
        for (Nino n : na) {
            sumaEdades += n.getEdad();
        }
        return (double) sumaEdades / na.size();
    }
    
    // --- e) Buscar al Nino con el carnet x (CI) ---
    public Nino buscarPorCi(int ci) {
        return leer(ci);
    }

    // --- f) Mostrar a los Ninos con la talla más alta ---
    public List<Nino> NinosConTallaMasAlta() {
        if (na.isEmpty()) {
            return new ArrayList<>();
        }
        
        double tallaMaxima = 0.0;
        for (Nino n : na) {
            try {
                double tallaActual = Double.parseDouble(n.getTalla().replace(",", "."));
                if (tallaActual > tallaMaxima) {
                    tallaMaxima = tallaActual;
                }
            } catch (NumberFormatException e) {
                // Ignora valores no numéricos para no romper el cálculo
            }
        }
        
        List<Nino> masAltos = new ArrayList<>();
        for (Nino n : na) {
            try {
                double tallaActual = Double.parseDouble(n.getTalla().replace(",", "."));
                if (tallaActual == tallaMaxima) {
                    masAltos.add(n);
                }
            } catch (NumberFormatException e) {
                // Ignora
            }
        }
        return masAltos;
    }
}