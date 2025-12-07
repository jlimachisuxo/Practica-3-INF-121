public class Libro {
    private String codLibro;
    private String titulo;
    private double precio; 

    // Constructor, Getters y Setters
    public Libro(String codLibro, String titulo, double precio) {
        this.codLibro = codLibro;
        this.titulo = titulo;
        this.precio = precio;
    }
    // ... Getters y Setters para todos los atributos ...
    public String getCodLibro() { return codLibro; }
    public double getPrecio() { return precio; }
    public String getTitulo() { return titulo; }

    @Override
    public String toString() {
        return "Libro [codLibro=" + codLibro + ", titulo=" + titulo + ", precio=" + precio + "]";
    }
}