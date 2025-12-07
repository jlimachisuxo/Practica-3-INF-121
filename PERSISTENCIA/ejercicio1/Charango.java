public class Charango {
    private String material;
    private int nroCuerdas;
    private boolean[] cuerdas; 

    // Constructor
    public Charango(String material, int nroCuerdas) {
        this.material = material;
        this.nroCuerdas = nroCuerdas;
        // La cuerdas son booleanos, por defecto son 'false' si no se especifican
        this.cuerdas = new boolean[10]; 
        // Inicializa las primeras nroCuerdas en true, simulando que están "puestas"
        for (int i = 0; i < nroCuerdas && i < 10; i++) {
            this.cuerdas[i] = true;
        }
    }

    // Constructor adicional para inicializar el estado de las 10 cuerdas
    public Charango(String material, int nroCuerdas, boolean[] estadoCuerdas) {
        this.material = material;
        this.nroCuerdas = nroCuerdas;
        this.cuerdas = estadoCuerdas; 
    }

    // Getters
    public String getMaterial() { 
        return material; 
    }
    public int getNroCuerdas() { 
        return nroCuerdas; 
    }
    public boolean[] getCuerdas() { 
        return cuerdas; 
    }

    // Setters
    public void setMaterial(String material) { 
        this.material = material; 
    }
    public void setNroCuerdas(int nroCuerdas) { 
        this.nroCuerdas = nroCuerdas; 
    }
    public void setCuerdas(boolean[] cuerdas) { 
        this.cuerdas = cuerdas; 
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean cuerda : cuerdas) {
            sb.append(cuerda ? "T" : "F");
        }
        return "Charango [Material: " + material + 
               ", Nro Cuerdas: " + nroCuerdas + 
               ", Estado Cuerdas (10): " + sb.toString() + "]";
    }
}