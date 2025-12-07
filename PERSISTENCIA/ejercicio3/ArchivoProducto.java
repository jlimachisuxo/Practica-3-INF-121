import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArchivoProducto {

    private String nomA; 


    private List<Producto> productos;
    private final Gson gson;

    public ArchivoProducto(String n) {
        this.nomA = n;

        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.productos = new ArrayList<>();
        cargarProductos(); // Cargar productos al inicializar
    }


    public void crearArchivo() {

        this.productos = new ArrayList<>();
        guardarListaEnArchivo();
        System.out.println("Archivo de productos inicializado: " + nomA);
    }

    private void cargarProductos() {
        try (Reader reader = new FileReader(nomA)) {
            Type listType = new TypeToken<ArrayList<Producto>>() {}.getType();
            List<Producto> productosCargados = gson.fromJson(reader, listType);

            if (productosCargados != null) {
                this.productos = productosCargados;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, se creará uno nuevo al guardar.");

        } catch (IOException e) {
            System.err.println("Error de I/O al cargar productos: " + e.getMessage());
        }
    }

    // Guarda la lista actual de productos al archivo JSON
    private void guardarListaEnArchivo() {
        try (Writer writer = new FileWriter(nomA)) {

            gson.toJson(this.productos, writer);
            System.out.println("Datos guardados con éxito en: " + nomA);
        } catch (IOException e) {
            System.err.println("Error de I/O al guardar productos: " + e.getMessage());
        }
    }


    /**
     * b) Implementa guardarProducto(Producto p) para almacenar productos.
     */
    public void guardaProducto(Producto p) {

        if (buscaProducto(p.getCodigo()) == null) {
            this.productos.add(p);
            guardarListaEnArchivo(); 
        } else {
            System.out.println("Error: Producto con código " + p.getCodigo() + " ya existe.");
        }
    }

    /**
     * c) Implementa buscaProducto(int c) buscando el código, para mostrar los datos.
     */
    public Producto buscaProducto(int c) {
        for (Producto p : this.productos) {
            if (p.getCodigo() == c) {
                return p;
            }
        }
        return null; // Retorna null si no lo encuentra
    }

    /**
     * d) Calcular el promedio de precios de los productos.
     */
    public double calcularPromedioPrecios() {
        if (this.productos.isEmpty()) {
            return 0.0;
        }

        double sumaPrecios = 0;
        for (Producto p : this.productos) {
            sumaPrecios += p.getPrecio();
        }

        return sumaPrecios / this.productos.size();
    }

    /**
     * e) Mostrar el producto mas caro.
     */
    public Producto obtenerProductoMasCaro() {
        if (this.productos.isEmpty()) {
            return null;
        }

        Producto masCaro = this.productos.get(0); 
        for (Producto p : this.productos) {
            if (p.getPrecio() > masCaro.getPrecio()) {
                masCaro = p;
            }
        }
        return masCaro;
    }

    // Método auxiliar para ver todos los productos cargados
    public List<Producto> getProductos() {
        return productos;
    }
}