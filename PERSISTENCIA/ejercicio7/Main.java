import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArchNino gestor = new ArchNino();

        if (gestor.listar().isEmpty()) {
            System.out.println("--- Creando datos iniciales ---");
            gestor.crear(new Nino("Juan", "Perez", "Lopez", 123, 5, "18.5", "110.5")); // Peso Inadecuado
            gestor.crear(new Nino("Ana", "Gomez", "Mendez", 456, 6, "22.0", "120.0")); // Peso Adecuado
            gestor.crear(new Nino("Luis", "Flores", "Rojas", 789, 5, "20.0", "110.5")); // Talla Más Alta
            gestor.crear(new Nino("Sofía", "Castro", "Vargas", 101, 7, "25.0", "115.0"));
            gestor.crear(new Nino("Pedro", "Mamani", "Cruz", 102, 7, "25.0", "110.5")); // Talla Más Alta

        }

        System.out.println("\n--- a) Listar todos los Ninos ---");
        gestor.listar().forEach(System.out::println);

        double P_MIN = 20.0; double P_MAX = 25.0; 
        double T_MIN = 115.0; double T_MAX = 120.0;

        System.out.println("\n--- b) Contar Ninos con peso/talla adecuado ---");
        int adecuados = gestor.contarNinosConPesoAdecuado(P_MIN, P_MAX, T_MIN, T_MAX);
        System.out.println("Ninos con peso/talla adecuado (ejemplo): " + adecuados);

        System.out.println("\n--- c) Mostrar Ninos con peso o talla inadecuada ---");
        List<Nino> inadecuados = gestor.NinosConPesoOTallaInadecuada(P_MIN, P_MAX, T_MIN, T_MAX);
        inadecuados.forEach(System.out::println);

        System.out.println("\n--- d) Promedio de edad ---");
        System.out.printf("El promedio de edad es: %.2f años\n", gestor.promedioEdad());

        System.out.println("\n--- e) Buscar al Nino con CI 456 ---");
        Nino encontrado = gestor.buscarPorCi(456);
        System.out.println(encontrado != null ? encontrado : "Nino no encontrado.");
        
        System.out.println("\n--- f) Ninos con la talla más alta ---");
        gestor.NinosConTallaMasAlta().forEach(System.out::println);
    }
}