import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Alimento {

    // Atributos privados
    private String nombre;
    private String fechaVencimiento;
    private int cantidad;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Constructor
    public Alimento(String nombre, String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
    }

    // --- Getters y Setters ---

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaComoLocalDate() {
        try {
            return LocalDate.parse(this.fechaVencimiento, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.err.println("Error al parsear la fecha: " + this.fechaVencimiento);
            return null; 
        }
    }

    public boolean estaVencido() {
        LocalDate fechaVenc = getFechaComoLocalDate();
        return fechaVenc != null && fechaVenc.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "nombre='" + nombre + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    // equals y hashCode (para facilitar la comparación y búsqueda)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alimento alimento = (Alimento) o;
        return Objects.equals(nombre.toLowerCase(), alimento.nombre.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}