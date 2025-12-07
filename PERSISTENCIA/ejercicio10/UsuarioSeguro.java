public class UsuarioSeguro {

    private String nombreUsuarioCifrado;
    private String contrasenaCifrada;
    
    // Campo extra para mostrar un atributo relevante a SI
    private long fechaCreacionTimestamp; 

    public UsuarioSeguro(String nombreUsuario, String contrasena) throws Exception {
        this.nombreUsuarioCifrado = CifradorAES.cifrar(nombreUsuario);
        this.contrasenaCifrada = CifradorAES.cifrar(contrasena);
        this.fechaCreacionTimestamp = System.currentTimeMillis();
    }

    // Constructor vacío requerido por Gson
    public UsuarioSeguro() {} 

    // --- Getters que devuelven los datos CIFRADOS (para persistencia) ---

    public String getNombreUsuarioCifrado() {
        return nombreUsuarioCifrado;
    }

    public String getContrasenaCifrada() {
        return contrasenaCifrada;
    }
    
    public long getFechaCreacionTimestamp() {
        return fechaCreacionTimestamp;
    }

    // --- Métodos de visualización (implican DESCIFRADO) ---

    public String getNombreUsuarioDescifrado() throws Exception {
        return CifradorAES.descifrar(nombreUsuarioCifrado);
    }

    public String getContrasenaDescifrada() throws Exception {
        return CifradorAES.descifrar(contrasenaCifrada);
    }

    @Override
    public String toString() {
        try {
            return "UsuarioSeguro [Nombre: " + getNombreUsuarioDescifrado() + 
                   ", Contraseña: " + getContrasenaDescifrada() + 
                   ", Creado en: " + new java.util.Date(fechaCreacionTimestamp) + "]";
        } catch (Exception e) {
            return "UsuarioSeguro [Error al descifrar]";
        }
    }
}