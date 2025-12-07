
public class Main {
    public static void main(String[] args) {
        // --- Carga de archivos al inicio ---
        ArchLibro archLibro = PersistenciaManejo.cargar("libros.json", ArchLibro.class);
        ArchPrestamo archPrestamo = PersistenciaManejo.cargar("prestamos.json", ArchPrestamo.class);
        ArchCliente archCliente = PersistenciaManejo.cargar("clientes.json", ArchCliente.class);

        if (archLibro.getLibroL().isEmpty()) {
            PersistenciaManejo.guardar(archLibro, archLibro.getNomArch());
            PersistenciaManejo.guardar(archPrestamo, archPrestamo.getNomArch());
            PersistenciaManejo.guardar(archCliente, archCliente.getNomArch());
        }

        
        System.out.println("--- a) Libros con precio entre 12.00 y 26.00 ---");
        archLibro.listarLibrosPorRangoDePrecio(12.00, 26.00).forEach(System.out::println);

        System.out.println("\n--- b) Ingreso total generado por L001 ---");
        double ingresoL001 = archPrestamo.calcularIngresoTotalPorLibro("L001", archLibro);
        System.out.printf("Ingreso total por L001: %.2f\n", ingresoL001);

        System.out.println("\n--- c) Libros que nunca fueron prestados ---");
        archLibro.listarLibrosNuncaPrestados(archPrestamo).forEach(System.out::println);

        System.out.println("\n--- d) Clientes que prestaron el libro L001 ---");
        archCliente.listarClientesPorLibro("L001", archPrestamo).forEach(System.out::println);

        System.out.println("\n--- e) El libro más prestado ---");
        archLibro.obtenerLibroMasPrestado(archPrestamo)
            .ifPresentOrElse(l -> System.out.println("Libro más prestado: " + l), () -> System.out.println("No hay préstamos."));

        System.out.println("\n--- f) El cliente que tuvo más préstamos ---");
        archCliente.obtenerClienteConMasPrestamos(archPrestamo)
            .ifPresentOrElse(c -> System.out.println("Cliente con más préstamos: " + c), () -> System.out.println("No hay préstamos."));
    }
}