public class Producto {

    private int codigo;
    private String nombre;
    private float precio;

    // Constructor por defecto necesario para Gson
    public Producto() {}

    // Constructor 
    public Producto(int codigo, String nombre, float precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Métodos Getter 
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
