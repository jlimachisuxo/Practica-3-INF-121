public class Main {
    public static void main(String[] args) {
     
        ArchRefri refri = new ArchRefri("RefrigeradorPrincipal");

        // 1. Cargar datos existentes (si los hay)
        ArchRefri.cargar();
        ArchRefri.mostrarTodos();

        // 2. a) Crear alimentos (con formato de fecha YYYY-MM-DD)
        System.out.println("\n--- PRUEBAS DE CREAR ---");
        ArchRefri.crearAlimento(new Alimento("Leche", "2025-12-31", 2));
        ArchRefri.crearAlimento(new Alimento("Huevos", "2025-11-20", 12));
        ArchRefri.crearAlimento(new Alimento("Jamón", "2025-12-04", 1)); // Este ya estaría vencido (fecha de hoy: 2025-12-05)
        ArchRefri.crearAlimento(new Alimento("Pan", "2026-01-15", 0)); // Cantidad 0 para prueba (c)
        ArchRefri.crearAlimento(new Alimento("Mantequilla", "2026-03-01", 3));

        ArchRefri.mostrarTodos();

        // 3. a) Modificar alimento
        System.out.println("\n--- PRUEBAS DE MODIFICAR ---");
        ArchRefri.modificarAlimento("Leche", "2026-01-10", 4);

        ArchRefri.mostrarTodos();

        // 4. b) Mostrar los alimentos que caducaron antes de una fecha dada X
        System.out.println("\n--- PRUEBAS DE VENCIMIENTO ANTES DE X ---");
        // Buscar vencidos antes del 1 de Diciembre de 2025
        ArchRefri.mostrarCaducadosAntesDe("2025-12-01");

        // 5. d) Buscar los alimentos ya vencidos (comparado con la fecha actual)
        ArchRefri.buscarAlimentosVencidos();

        // 6. e) Mostrar el alimento que tenga más cantidad
        ArchRefri.mostrarAlimentoConMasCantidad();

        // 7. a) Eliminar por nombre
        System.out.println("\n--- PRUEBAS DE ELIMINAR POR NOMBRE ---");
        ArchRefri.eliminarAlimento("Jamón");

        ArchRefri.mostrarTodos();

        // 8. c) Eliminar los alimentos que tengan cantidad 0
        ArchRefri.eliminarCantidadCero();

        ArchRefri.mostrarTodos();
    }
}