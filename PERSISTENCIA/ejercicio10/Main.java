public class Main {
    public static void main(String[] args) {
        try {
            GestorUsuarios gestor = new GestorUsuarios();

            // 1. Agregar usuarios y guardar en usuarios_seguro.txt
            System.out.println("--- Agregando Usuarios ---");
            gestor.agregarUsuario("alice", "P4sswOrd123!");
            gestor.agregarUsuario("bob", "SuperSeguro#42");
            gestor.agregarUsuario("carlos", "MiClaveSecreta");

            // 2. Mostrar todos los registros (descifrados)
            gestor.mostrarTodosLosRegistros();

            // 3. Buscar un usuario por su nombre
            System.out.println("\n--- Buscando Usuario ---");
            String nombreABuscar = "alice";
            UsuarioSeguro usuarioEncontrado = gestor.buscarUsuario(nombreABuscar);

            if (usuarioEncontrado != null) {
                System.out.println("🎉 Usuario '" + nombreABuscar + "' encontrado:");

                System.out.println(usuarioEncontrado); 
            } else {
                System.out.println("Usuario '" + nombreABuscar + "' no encontrado.");
            }
            
            // 4. Buscar un usuario que no existe
            System.out.println("\n--- Buscando Usuario Inexistente ---");
            nombreABuscar = "david";
            usuarioEncontrado = gestor.buscarUsuario(nombreABuscar);
            
            if (usuarioEncontrado == null) {
                 System.out.println("Usuario '" + nombreABuscar + "' no encontrado, como se esperaba.");
            }

        } catch (Exception e) {
            System.err.println("\nOcurrió un error grave en la aplicación:");
            e.printStackTrace();
        }
    }
}