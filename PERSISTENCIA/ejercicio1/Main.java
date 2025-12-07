import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArchivoCharango gestion = new ArchivoCharango();
        
        System.out.println("--- 1. Inicialización ---");
        List<Charango> charangos = new ArrayList<>();
 
        charangos.add(new Charango("Naranjillo", 5)); // 5T, 5F -> 5 False
        charangos.add(new Charango("Jacarandá", 10)); // 10T, 0F -> 0 False
        charangos.add(new Charango("Naranjillo", 8)); // 8T, 2F -> 2 False
        // Charango con muchas cuerdas en false (para prueba de eliminación)
        boolean[] cuerdasMalas = {true, false, false, false, false, false, false, false, true, false}; // 7 False
        charangos.add(new Charango("Koa", 3, cuerdasMalas)); // 3T, 7F -> 7 False (Debería ser eliminado)

        gestion.guardarCharangos(charangos);
        
        // --- 2. Cargar Charangos y Operación (b) Eliminar ---
        System.out.println("\n--- 2. Operación (b): Eliminar Charangos ---");
        List<Charango> listaActual = gestion.cargarCharangos();
        System.out.println("Lista antes de eliminar (4 Charangos):");
        listaActual.forEach(System.out::println);
        
        List<Charango> listaDespuesEliminar = gestion.eliminarCharangosPorCuerdas(listaActual);
        
        System.out.println("\nLista después de eliminar (Eliminado el de 7 False):");
        listaDespuesEliminar.forEach(System.out::println);

        gestion.guardarCharangos(listaDespuesEliminar); 
        
        // --- 3. Operación (c) Listar por Material ---
        System.out.println("\n--- 3. Operación (c): Listar por Material (Naranjillo) ---");
        List<Charango> charangosNaranjillo = gestion.listarCharangosPorMaterial(listaDespuesEliminar, "Naranjillo");
        charangosNaranjillo.forEach(System.out::println);

        // --- 4. Operación (d) Buscar por NroCuerdas ---
        System.out.println("\n--- 4. Operación (d): Buscar Charangos con 10 Cuerdas ---");
        List<Charango> charangos10Cuerdas = gestion.buscarCharangosPorNroCuerdas(listaDespuesEliminar, 10);
        charangos10Cuerdas.forEach(System.out::println);

        // --- 5. Operación (e) Ordenar por Material ---
        System.out.println("\n--- 5. Operación (e): Ordenar por Material ---");

        List<Charango> listaOrdenada = gestion.cargarCharangos(); 
        gestion.ordenarCharangosPorMaterial(listaOrdenada);
        
        System.out.println("Lista ordenada alfabéticamente por Material:");
        listaOrdenada.forEach(System.out::println);
        
        gestion.guardarCharangos(listaOrdenada);
    }
}