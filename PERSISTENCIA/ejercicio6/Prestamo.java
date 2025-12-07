import java.util.Date;

public class Prestamo {
    private String codCliente;
    private String codLibro;
    private Date fechaPrestamo; 
    private int cantidad; 
    
    // Constructor, Getters y Setters
    public Prestamo(String codCliente, String codLibro, Date fechaPrestamo, int cantidad) {
        this.codCliente = codCliente;
        this.codLibro = codLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.cantidad = cantidad;
    }
    // ... Getters y Setters para todos los atributos ...
    public String getCodLibro() { return codLibro; }
    public String getCodCliente() { return codCliente; }
    public int getCantidad() { return cantidad; }
}