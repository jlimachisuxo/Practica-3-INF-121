import java.util.ArrayList;
import java.util.List;

public class Farmacia {
    private String nombreFarmacia;
    private int sucursal;
    private String direccion;
    private int nroMedicamentos; 
  
    private List<Medicamento> medicamentos; 

    // Constructor sin argumentos (necesario para Gson)
    public Farmacia() {
        this.nroMedicamentos = 0; 
        this.medicamentos = new ArrayList<>();
    }
    
    // Constructor
    public Farmacia(String nombreFarmacia, int sucursal, String direccion) {
        this.nombreFarmacia = nombreFarmacia;
        this.sucursal = sucursal;
        this.direccion = direccion;
        this.nroMedicamentos = 0;
        this.medicamentos = new ArrayList<>();
    }

    public void leer() { 
        /* Lógica para leer */ 
    }

    // Método mostrar()
    public void mostrar() {
        System.out.println("Farmacia: " + nombreFarmacia +
                           " | Sucursal Nro: " + sucursal +
                           " | Dir: " + direccion +
                           " | Medicamentos: " + nroMedicamentos);
    }

    // Métodos getter
    public String getDireccion() {
        return direccion;
    }

    public int getSucursal() {
        return sucursal;
    }

    public String getNombreFarmacia() {
        return nombreFarmacia;
    }
    
    // Getter para la lista de medicamentos
    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }
    
    public int getNroMedicamentos() {
        return nroMedicamentos;
    }

    public void setNroMedicamentos(int nroMedicamentos) {
        this.nroMedicamentos = nroMedicamentos;
    }
    
    // Método mostrarMedicamentos(x)
    public void mostrarMedicamentos(String tipoBuscado) {
        System.out.println("  Medicamentos de tipo '" + tipoBuscado + "' en " + nombreFarmacia + ":");
        boolean encontrado = false;
        for (Medicamento med : medicamentos) { 
            if (med.getTipo().equalsIgnoreCase(tipoBuscado)) {
                med.mostrar();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("  No se encontraron medicamentos de tipo '" + tipoBuscado + "'.");
        }
    }
    
    // Método buscaMedicamento(x)
    public boolean buscaMedicamento(String nombreMedicamento) {
        for (Medicamento med : medicamentos) {
            if (med.getNombre().equalsIgnoreCase(nombreMedicamento)) { 
                return true;
            }
        }
        return false;
    }
    
    // Método adicionarMedicamento: Actualiza nroMedicamentos
    public void adicionarMedicamento(Medicamento medicamento) {
        if (medicamentos.size() < 100) { 
            medicamentos.add(medicamento);
            this.nroMedicamentos = medicamentos.size(); // Sincroniza el atributo
        } else {
            System.out.println("Advertencia: Límite de 100 medicamentos alcanzado en " + nombreFarmacia);
        }
    }
    
    public void removerMedicamento(Medicamento medicamento) {
        if (medicamentos.remove(medicamento)) {
            this.nroMedicamentos = medicamentos.size(); // Sincroniza el atributo después de remover
        }
    }
}