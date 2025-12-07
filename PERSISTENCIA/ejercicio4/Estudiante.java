public class Estudiante {
    private String ru;
    private String nombre;
    private String paterno;
    private String materno;
    private int edad;

    // Constructor
    public Estudiante(String ru, String nombre, String paterno, String materno, int edad) {
        this.ru = ru;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.edad = edad;
    }

    // Getters y Setters
    public String getRu() {
        return ru;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Estudiante [RU=" + ru + ", Nombre=" + nombre + " " + paterno + ", Edad=" + edad + "]";
    }
}