public class Medicamento {
    // Atributos privados
    private String nombre;
    private int codMedicamento;
    private String tipo;
    private double precio;

    // Constructor sin argumentos (necesario para Gson)
    public Medicamento() {
    }

    // Constructor 
    public Medicamento(String nombre, int codMedicamento, String tipo, double precio) {
        this.nombre = nombre;
        this.codMedicamento = codMedicamento;
        this.tipo = tipo;
        this.precio = precio;
    }

    public void leer() {
        // Lógica para leer/asignar valores a los atributos
    }

    // Método mostrar()
    public void mostrar() {
        System.out.println("  Nombre: " + nombre + ", Cod: " + codMedicamento +
                           ", Tipo: " + tipo + ", Precio: " + precio);
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }
    

    public String getNombre() {
        return nombre;
    }
}