public class Nino extends Persona {
    private int edad;
    private String peso;
    private String talla; 

    // Constructor, llamando al constructor de Persona
    public Nino(String nombre, String apellidoPaterno, String apellidoMaterno, int ci, int edad, String peso, String talla) {
        super(nombre, apellidoPaterno, apellidoMaterno, ci);
        this.edad = edad;
        this.peso = peso;
        this.talla = talla;
    }

    // Getters
    public int getEdad() { return edad; }
    public String getPeso() { return peso; }
    public String getTalla() { return talla; }

    // Setters
    public void setEdad(int edad) { this.edad = edad; }
    public void setPeso(String peso) { this.peso = peso; }
    public void setTalla(String talla) { this.talla = talla; }

    @Override
    public String toString() {
        return "Niño{" + "nombre='" + getNombre() + '\'' + ", ci=" + getCi() + 
               ", edad=" + edad + ", peso='" + peso + '\'' + ", talla='" + talla + '\'' + '}';
    }

    public boolean tienePesoAdecuado(double pesoIdealMin, double pesoIdealMax, double tallaIdealMin, double tallaIdealMax) {

        try {
            double p = Double.parseDouble(peso.replace(",", ".")); 
            double t = Double.parseDouble(talla.replace(",", "."));

            return (p >= pesoIdealMin && p <= pesoIdealMax) && (t >= tallaIdealMin && t <= tallaIdealMax);
        } catch (NumberFormatException e) {
            System.err.println("Error: Peso o Talla no son números válidos para Niño con CI " + getCi());
            return false;
        }
    }
}
