import java.util.*;
import java.util.stream.Collectors;

public class ArchLibro {
    private String nomArch = "libros.json";
    private List<Libro> libroL = new ArrayList<>();

    public ArchLibro() {}
    public List<Libro> getLibroL() { return libroL; }
    public String getNomArch() { return nomArch; }

    /**
     * a) Listar los libros cuyo precio estén entre 2 valores (x e y).
     */
    public List<Libro> listarLibrosPorRangoDePrecio(double x, double y) {
        return libroL.stream()
                .filter(libro -> libro.getPrecio() >= x && libro.getPrecio() <= y)
                .collect(Collectors.toList());
    }

    /**
     * c) Mostrar la lista de libros que nunca fueron vendidos (prestados).
     */
    public List<Libro> listarLibrosNuncaPrestados(ArchPrestamo archPrestamo) {
        Set<String> codigosPrestados = archPrestamo.getPrestamo().stream()
                .map(Prestamo::getCodLibro)
                .collect(Collectors.toSet());

        return libroL.stream()
                .filter(libro -> !codigosPrestados.contains(libro.getCodLibro()))
                .collect(Collectors.toList());
    }

    /**
     * e) Definir el libro más prestado.
     */
    public Optional<Libro> obtenerLibroMasPrestado(ArchPrestamo archPrestamo) {
        Map<String, Integer> prestamosPorLibro = archPrestamo.getPrestamo().stream()
                .collect(Collectors.groupingBy(
                        Prestamo::getCodLibro,
                        Collectors.summingInt(Prestamo::getCantidad)
                ));

        if (prestamosPorLibro.isEmpty()) {
            return Optional.empty();
        }

        String codLibroMasPrestado = prestamosPorLibro.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();

        return libroL.stream()
                .filter(l -> l.getCodLibro().equals(codLibroMasPrestado))
                .findFirst();
    }
}