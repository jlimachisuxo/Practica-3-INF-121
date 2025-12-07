public class Persona {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int ci; 

    // Constructor, Getters y Setters...
    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, int ci) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.ci = ci;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public int getCi() { return ci; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCi(int ci) { this.ci = ci; }

}
