import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

    private static final String ARCHIVO_DATOS = "usuarios_seguro.txt";
    private final Gson gson;
    private List<UsuarioSeguro> listaUsuarios;

    public GestorUsuarios() {

        this.gson = new GsonBuilder().setPrettyPrinting().create(); 
        this.listaUsuarios = cargarUsuarios();
    }

    /**
     * Carga los registros desde el archivo usuarios_seguro.txt.
     */
    private List<UsuarioSeguro> cargarUsuarios() {
        try (FileReader reader = new FileReader(ARCHIVO_DATOS)) {
            Type tipoLista = new TypeToken<ArrayList<UsuarioSeguro>>() {}.getType();

            List<UsuarioSeguro> usuarios = gson.fromJson(reader, tipoLista);
            return usuarios != null ? usuarios : new ArrayList<>();
        } catch (IOException e) {
  
            System.out.println("Archivo de datos no encontrado. Creando nueva lista.");
            return new ArrayList<>();
        }
    }

    /**
     * Guarda la lista actual de usuarios en el archivo.
     */
    private void guardarUsuarios() {
        try (FileWriter writer = new FileWriter(ARCHIVO_DATOS)) {
   
            gson.toJson(listaUsuarios, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    /**
     * Agrega un nuevo usuario y guarda la lista.
     */
    public void agregarUsuario(String nombreUsuario, String contrasena) throws Exception {
        UsuarioSeguro nuevoUsuario = new UsuarioSeguro(nombreUsuario, contrasena);
        listaUsuarios.add(nuevoUsuario);
        guardarUsuarios();
        System.out.println("Usuario " + nombreUsuario + " cifrado y guardado exitosamente.");
    }

    /**
     * Muestra todos los registros descifrando el nombre y la contraseña.
     */
    public void mostrarTodosLosRegistros() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay registros de usuarios para mostrar.");
            return;
        }
        System.out.println("\n--- Registros Descifrados ---");
        for (UsuarioSeguro usuario : listaUsuarios) {
            System.out.println(usuario);
        }
        System.out.println("---------------------------------");
    }

    /**
     * Busca un usuario por su nombre descifrando los registros.
     */
    public UsuarioSeguro buscarUsuario(String nombreBuscado) {
        try {
            for (UsuarioSeguro usuario : listaUsuarios) {
                // Descifra el nombre de usuario para comparar con el nombre buscado
                String nombreDescifrado = usuario.getNombreUsuarioDescifrado();
                if (nombreDescifrado.equalsIgnoreCase(nombreBuscado)) {
                    return usuario;
                }
            }
        } catch (Exception e) {
            System.err.println("Error durante la búsqueda y descifrado: " + e.getMessage());
        }
        return null; // Usuario no encontrado
    }
}