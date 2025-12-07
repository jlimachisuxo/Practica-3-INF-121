public class Main {
    

    public static void main(String[] args) {
        String archivoPersistencia = "farmacias.json";
        
        // 1. Inicialización y Limpieza (opcional)
        
        ArchFarmacia arch = new ArchFarmacia(archivoPersistencia);

        // 2. Creación y Adición de Datos de Prueba (si la lista está vacía)
        if (arch.farmacias.isEmpty()) { 
            System.out.println("\n--- Creando Datos Iniciales de Prueba ---");

            // Medicamentos
            Medicamento m1 = new Medicamento("Tapsin", 101, "Resfrio", 5.50);
            Medicamento m2 = new Medicamento("Paracetamol", 102, "Dolor", 2.00);
            Medicamento m3 = new Medicamento("Jarabe A", 103, "Tos", 8.00);
            Medicamento m4 = new Medicamento("Ibuprofeno", 104, "Dolor", 3.50);
            Medicamento m5 = new Medicamento("Aspirina", 105, "Dolor", 1.50);
            Medicamento m6 = new Medicamento("Jarabe B", 106, "Tos", 9.50);

            // Farmacia 1 (Origen)
            Farmacia f1 = new Farmacia("FarmaTotal", 1, "Av. Siempre Viva 742");
            f1.adicionarMedicamento(m1);
            f1.adicionarMedicamento(m2);
            f1.adicionarMedicamento(m3); // Medicamento a mover
            arch.adicionar(f1); // Esto guarda en el JSON

            // Farmacia 2 (Destino)
            Farmacia f2 = new Farmacia("Salud Max", 2, "Calle 10 Nro 50");
            f2.adicionarMedicamento(m4);
            f2.adicionarMedicamento(m5);
            arch.adicionar(f2); // Esto guarda en el JSON
            
            // Farmacia 3 (Para ordenar)
            Farmacia f3 = new Farmacia("Farmasur", 3, "Blvd. Central 100");
            f3.adicionarMedicamento(m6); 
            arch.adicionar(f3);
        }

        // a) Mostrar los medicamentos para la tos, de la Sucursal número X
        arch.mostrarMedicamentosTosSucursal(1); // Sucursal 1 tiene Jarabe A
        arch.mostrarMedicamentosTosSucursal(2); // Sucursal 2 no tiene Jarabe Tos

        // b) Mostrar el número de sucursal y su dirección que tienen el medicamento “Tapsin”.
        arch.buscarSucursalesConMedicamento("Tapsin");

        // c) Buscar medicamentos por tipo.
        arch.buscarMedicamentosPorTipoGlobal("Dolor");

        // d) Ordenar las farmacias según su dirección en orden alfabético.
        arch.ordenarFarmaciasPorDireccion();
        arch.listar(); 

        // e) Mover los medicamentos de tipo x de la farmacia y a la farmacia z.
        // Mover todos los medicamentos de tipo "Tos" de la Sucursal 1 a la Sucursal 2
        arch.moverMedicamentos("Tos", 1, 2); 
        
        // Verificar el movimiento
        System.out.println("\n--- Estado Final Después del Movimiento ---");
        arch.listar(); 
    }
}
