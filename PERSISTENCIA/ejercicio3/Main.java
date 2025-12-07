public class Main {
    
    public static void main(String[] args) {

        final String NOMBRE_ARCHIVO = "productos.json";
        ArchivoProducto gestor = new ArchivoProducto(NOMBRE_ARCHIVO);

        // b) Implementa guardarProducto(Producto p)
        // ----------------------------------------------------
        System.out.println("\n*** b) GUARDAR PRODUCTOS ***");
        gestor.guardaProducto(new Producto(101, "Laptop", 1250.50f));
        gestor.guardaProducto(new Producto(102, "Mouse", 25.99f));
        gestor.guardaProducto(new Producto(103, "Monitor", 350.00f));
        gestor.guardaProducto(new Producto(104, "Teclado", 75.00f));

        gestor.guardaProducto(new Producto(101, "Otro Laptop", 1000.00f));

        // Mostrar todos los productos guardados (opcional, para verificación)
        System.out.println("\n*** Lista de Productos Actual ***");
        for(Producto p : gestor.getProductos()) {
            System.out.println(p);
        }

        // ----------------------------------------------------
        // c) Implementa buscaProducto(int c)
        // ----------------------------------------------------
        System.out.println("\n*** c) BUSCAR PRODUCTO (Código 103) ***");
        int codigoBuscado = 103;
        Producto productoEncontrado = gestor.buscaProducto(codigoBuscado);

        if (productoEncontrado != null) {
            System.out.println("Producto encontrado: " + productoEncontrado);
        } else {
            System.out.println("Producto con código " + codigoBuscado + " no encontrado.");
        }

        // ----------------------------------------------------
        // d) Calcular el promedio de precios de los productos
        // ----------------------------------------------------
        System.out.println("\n*** d) PROMEDIO DE PRECIOS ***");
        double promedio = gestor.calcularPromedioPrecios();
    
        System.out.println("El promedio de precios es: $" + String.format("%.2f", promedio));

        // ----------------------------------------------------
        // e) Mostrar el producto mas caro
        // ----------------------------------------------------
        System.out.println("\n*** e) PRODUCTO MÁS CARO ***");
        Producto masCaro = gestor.obtenerProductoMasCaro();
        if (masCaro != null) {
            System.out.println("El producto más caro es: " + masCaro);
        } else {
            System.out.println("No hay productos para determinar el más caro.");
        }
    }
    
}
