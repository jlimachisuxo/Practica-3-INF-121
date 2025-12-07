import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PersistenciaManejo {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Método genérico para guardar (Serializar)
    public static <T> void guardar(T objetoArchivo, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            GSON.toJson(objetoArchivo, writer);
            System.out.println("Datos guardados en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método genérico para cargar (Deserializar)
    public static <T> T cargar(String nombreArchivo, Class<T> claseArchivo) {
        try (FileReader reader = new FileReader(nombreArchivo)) {
            return GSON.fromJson(reader, claseArchivo);
        } catch (IOException e) {
            // Si el archivo no existe o hay un error, devuelve una nueva instancia
            try {
                return claseArchivo.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}
