
public class Main {
    public static void main(String[] args) {
        // Inicialización
        ArchZoo archivo = new ArchZoo("zoologicos.json");
        
        // Crear Zoológicos
        Zoologico zoo1 = new Zoologico(1, "Zoo Metropolitano");
        zoo1.agregarAnimal(new Animal("Mamífero", "León", 5));
        zoo1.agregarAnimal(new Animal("Ave", "Águila", 3));
        zoo1.agregarAnimal(new Animal("Reptil", "Serpiente", 8));

        Zoologico zoo2 = new Zoologico(2, "Zoo del Este");
        zoo2.agregarAnimal(new Animal("Mamífero", "Tigre", 2));
        zoo2.agregarAnimal(new Animal("Pez", "Payaso", 50));
        
        Zoologico zoo3 = new Zoologico(3, "Zoo Vacío");
        
        Zoologico zoo4 = new Zoologico(4, "Zoo Máximo");
        zoo4.agregarAnimal(new Animal("Mamífero", "Elefante", 1));
        zoo4.agregarAnimal(new Animal("Ave", "Loro", 10));
        zoo4.agregarAnimal(new Animal("Reptil", "Cocodrilo", 2));
        zoo4.agregarAnimal(new Animal("Insecto", "Mariposa", 100)); // Más variedad

        // a) Implementar el método crear
        System.out.println("--- PRUEBA A: CREAR ---");
        archivo.crear(zoo1);
        archivo.crear(zoo2);
        archivo.crear(zoo3);
        archivo.crear(zoo4);
        archivo.mostrarContenido();

        // --- PRUEBA A: MODIFICAR ---
        System.out.println("\n--- PRUEBA A: MODIFICAR (Zoo2) ---");
        zoo2.setNombre("Zoo del Oeste Modificado");
        zoo2.agregarAnimal(new Animal("Mamífero", "Panda", 1)); // Agregar una variedad
        archivo.modificar(zoo2);
        archivo.mostrarContenido();
        
        // --- PRUEBA B: Listar mayor variedad ---
        archivo.listarMayorVariedad(); // Debe mostrar Zoo4 (4 variedades)

        // --- PRUEBA D: Mostrar animales de especie x ---
        archivo.mostrarAnimalesPorEspecie("Mamífero");
        archivo.mostrarAnimalesPorEspecie("Reptil");
        
        // --- PRUEBA E: Mover animales ---
        System.out.println("\n--- PRUEBA E: MOVER ANIMALES (Zoo4 a Zoo1) ---");
        archivo.moverAnimales(4, 1);
        archivo.mostrarContenido();
        
        // --- PRUEBA C: Listar y eliminar vacíos ---
        // El zoo3 y zoo4 deben ser eliminados.
        archivo.listarYEliminarVacios();
        archivo.mostrarContenido(); // Solo debe quedar Zoo1 y Zoo2

        // --- PRUEBA A: ELIMINAR ---
        System.out.println("\n--- PRUEBA A: ELIMINAR (Zoo2) ---");
        archivo.eliminar(2);
        archivo.mostrarContenido(); // Solo debe quedar Zoo1
    }
}