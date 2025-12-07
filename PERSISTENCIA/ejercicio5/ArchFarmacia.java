import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArchFarmacia {

    private String na; 
    // Atributo interno: Lista para manejar la colección de farmacias
    List<Farmacia> farmacias;
    
    // Constructor (inicializa la lista de farmacias y el nombre del archivo)
    public ArchFarmacia(String na) {
        this.na = na;
        this.farmacias = new ArrayList<>();
        // Al instanciar, intenta cargar datos existentes
        cargarArchivo(); 
    }
    
    // Método crearArchivo(na) - Reinicia la lista y persiste
    public void crearArchivo(String na) {
        this.na = na;
        this.farmacias.clear();
        guardarArchivo();
        System.out.println("Archivo " + na + " creado y vaciado.");
    }
    
    // Método adicionar() - Añade una farmacia a la lista en memoria y persiste
    public void adicionar(Farmacia f) {
        farmacias.add(f);
        guardarArchivo(); 
    }

 
    // Carga los datos del archivo JSON a la lista 'farmacias'
    private void cargarArchivo() {
        try (FileReader reader = new FileReader(na)) {
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Farmacia>>() {}.getType();
            List<Farmacia> loadedList = gson.fromJson(reader, listType);
            
            if (loadedList != null) {
                this.farmacias = loadedList;
                System.out.println("Datos cargados exitosamente de " + na);
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo " + na + ". Se inicializa una lista vacía.");
        }
    }


    public void guardarArchivo() {
        try (FileWriter writer = new FileWriter(na)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this.farmacias, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método listar()
    public void listar() {
        if (farmacias.isEmpty()) {
            System.out.println("No hay farmacias para listar.");
            return;
        }
        System.out.println("--- Listado de Farmacias ---");
        for (Farmacia f : farmacias) {
            f.mostrar();
            // Itera sobre la lista de medicamentos
            for (Medicamento med : f.getMedicamentos()) {
                med.mostrar();
            }
            System.out.println("---------------------------");
        }
    }

    // a) Mostrar los medicamentos para la tos, de la Sucursal número X
    public void mostrarMedicamentosTosSucursal(int numSucursal) {
        final String TIPO_TOS = "Tos";
        System.out.println("\n--- a) Medicamentos para la Tos en la Sucursal Nro: " + numSucursal + " ---");
        
        for (Farmacia f : farmacias) {
            if (f.getSucursal() == numSucursal) {
                System.out.println("Farmacia: " + f.getNombreFarmacia());
                boolean encontrado = false;
                
                // Iteración usando List<Medicamento>
                for (int i = 0; i < f.getNroMedicamentos(); i++) {
                    Medicamento med = f.getMedicamentos().get(i); 
                    if (med.getTipo().equalsIgnoreCase(TIPO_TOS)) {
                        med.mostrar();
                        encontrado = true;
                    }
                }
                
                if (!encontrado) {
                    System.out.println("  No se encontraron medicamentos de tipo 'Tos' en esta sucursal.");
                }
                return;
            }
        }
        System.out.println("  No se encontró la sucursal número " + numSucursal + ".");
    }
    
    // b) Mostrar el número de sucursal y su dirección que tienen el medicamento “Tapsin”.
    public void buscarSucursalesConMedicamento(String nombreMed) {
        System.out.println("\n--- b) Sucursales con el medicamento '" + nombreMed + "' ---");
        boolean encontrado = false;
        for (Farmacia f : farmacias) {
            if (f.buscaMedicamento(nombreMed)) {
                System.out.println(" - Sucursal Nro: " + f.getSucursal() + ", Dirección: " + f.getDireccion());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("  El medicamento '" + nombreMed + "' no se encontró en ninguna sucursal.");
        }
    }

    // c) Buscar medicamentos por tipo.
    public void buscarMedicamentosPorTipoGlobal(String tipoBuscado) {
        System.out.println("\n--- c) Búsqueda Global de Medicamentos de Tipo: '" + tipoBuscado + "' ---");
        boolean encontrado = false;
        for (Farmacia f : farmacias) {
            System.out.println("-> Farmacia: " + f.getNombreFarmacia() + " (Sucursal: " + f.getSucursal() + ")");
            for (Medicamento med : f.getMedicamentos()) {
                if (med.getTipo().equalsIgnoreCase(tipoBuscado)) {
                    med.mostrar();
                    encontrado = true;
                }
            }
        }
        if (!encontrado) {
            System.out.println("  No se encontraron medicamentos de tipo '" + tipoBuscado + "' en el sistema.");
        }
    }

    // d) Ordenar las farmacias según su dirección en orden alfabético.
    public void ordenarFarmaciasPorDireccion() {
        System.out.println("\n--- d) Farmacias Ordenadas por Dirección ---");
        // Ordena la lista de farmacias usando el comparador de direcciones
        Collections.sort(farmacias, Comparator.comparing(Farmacia::getDireccion));
        
        for (Farmacia f : farmacias) {
            f.mostrar();
        }
        guardarArchivo(); 
    }

    // e) Mover los medicamentos de tipo x de la farmacia y a la farmacia z.
    public void moverMedicamentos(String tipo, int sucursalOrigen, int sucursalDestino) {
        System.out.println("\n--- e) Mover medicamentos de tipo '" + tipo + "' de Sucursal " + sucursalOrigen + " a Sucursal " + sucursalDestino + " ---");
        
        Farmacia origen = farmacias.stream()
                .filter(f -> f.getSucursal() == sucursalOrigen)
                .findFirst().orElse(null);
        
        Farmacia destino = farmacias.stream()
                .filter(f -> f.getSucursal() == sucursalDestino)
                .findFirst().orElse(null);

        if (origen == null || destino == null) {
            System.out.println("Error: Sucursal de origen (" + sucursalOrigen + ") o destino (" + sucursalDestino + ") no encontrada.");
            return;
        }

        List<Medicamento> aMover = new ArrayList<>();
        
        // 1. Identificar los medicamentos a mover
        for (Medicamento med : origen.getMedicamentos()) {
            if (med.getTipo().equalsIgnoreCase(tipo)) {
                aMover.add(med);
            }
        }
        
        int movidos = 0;
        
        // 2. Moverlos y removerlos
        for (Medicamento med : aMover) {
            if (destino.getNroMedicamentos() < 100) { 

                destino.adicionarMedicamento(med);

                origen.removerMedicamento(med); 
                movidos++;
            } else {
                System.out.println("Advertencia: El destino está lleno. " + med.getNombre() + " no fue movido.");
            }
        }

        System.out.println(" -> Se movieron " + movidos + " medicamentos de tipo '" + tipo + "'.");
        guardarArchivo(); 
    }
}