
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class ArchPrestamo {
    private String nomArch = "prestamos.json";
    private List<Prestamo> prestamo = new ArrayList<>();

    public ArchPrestamo() {}
    public List<Prestamo> getPrestamo() { return prestamo; }
    public String getNomArch() { return nomArch; }

    /**
     * b) Calcular el ingreso total generado por un libro especifico.
     */
    public double calcularIngresoTotalPorLibro(String codLibro, ArchLibro archLibro) {

        Optional<Libro> libroOpt = archLibro.getLibroL().stream()
                .filter(l -> l.getCodLibro().equals(codLibro))
                .findFirst();

        if (!libroOpt.isPresent()) {
            System.out.println("Error: Libro con código " + codLibro + " no encontrado.");
            return 0.0;
        }

        double precioLibro = libroOpt.get().getPrecio();
        return prestamo.stream()
                .filter(p -> p.getCodLibro().equals(codLibro))
                .mapToDouble(p -> p.getCantidad() * precioLibro)
                .sum();
    }
    
}