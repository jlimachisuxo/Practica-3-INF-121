public class Animal {
    private String especie;
    private String nombre;
    private int cantidad;

    // Constructor
    public Animal(String especie, String nombre, int cantidad) {
        this.especie = especie;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "  [Especie: " + especie + ", Nombre: " + nombre + ", Cantidad: " + cantidad + "]";
    }
}