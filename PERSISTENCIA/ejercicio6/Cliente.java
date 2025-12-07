public class Cliente {
    private String codCliente;
    private String ci;
    private String nombre;
    private String apellido;

    // Constructor, Getters y Setters
    public Cliente(String codCliente, String ci, String nombre, String apellido) {
        this.codCliente = codCliente;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    // ... Getters y Setters para todos los atributos ...
    public String getCodCliente() { return codCliente; }

    @Override
    public String toString() {
        return "Cliente [codCliente=" + codCliente + ", nombre=" + nombre + " " + apellido + "]";
    }
}