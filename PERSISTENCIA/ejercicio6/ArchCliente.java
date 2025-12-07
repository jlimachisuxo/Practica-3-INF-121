import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ArchCliente {
    private String nomArch = "clientes.json";
    private List<Cliente> clienteC = new ArrayList<>();

    public ArchCliente() {}
    public List<Cliente> getClienteC() { return clienteC; }
    public String getNomArch() { return nomArch; }

    /**
     * d) Mostrar a todos los clientes que compraron (tomaron prestado) un libro especifico.
     */
    public List<Cliente> listarClientesPorLibro(String codLibro, ArchPrestamo archPrestamo) {

        Set<String> codigosClientePrestamo = archPrestamo.getPrestamo().stream()
                .filter(p -> p.getCodLibro().equals(codLibro))
                .map(Prestamo::getCodCliente)
                .collect(Collectors.toSet());
        return clienteC.stream()
                .filter(cliente -> codigosClientePrestamo.contains(cliente.getCodCliente()))
                .collect(Collectors.toList());
    }

    /**
     * f) Mostrar el cliente que tuvo más préstamos (por transacciones).
     */
    public Optional<Cliente> obtenerClienteConMasPrestamos(ArchPrestamo archPrestamo) {
        Map<String, Long> conteoPrestamosPorCliente = archPrestamo.getPrestamo().stream()
                .collect(Collectors.groupingBy(
                        Prestamo::getCodCliente,
                        Collectors.counting()
                ));

        if (conteoPrestamosPorCliente.isEmpty()) {
            return Optional.empty();
        }

        String codClienteConMasPrestamos = conteoPrestamosPorCliente.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();

        return clienteC.stream()
                .filter(c -> c.getCodCliente().equals(codClienteConMasPrestamos))
                .findFirst();
    }
}