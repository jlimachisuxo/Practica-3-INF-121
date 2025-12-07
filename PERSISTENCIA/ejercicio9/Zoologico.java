import java.util.ArrayList;
import java.util.List;

public class Zoologico {
    private int id;
    private String nombre;
    private int nroAnimales; 
    private List<Animal> animales; 

    // Constructor
    public Zoologico(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.nroAnimales = 0;
        this.animales = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getNroAnimales() {
        this.nroAnimales = this.animales.size(); 
        return this.nroAnimales;
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    // Métodos para manejar la lista
    public void agregarAnimal(Animal animal) {
        this.animales.add(animal);
        this.nroAnimales = this.animales.size();
    }

    public void eliminarAnimal(Animal animal) {
        this.animales.remove(animal);
        this.nroAnimales = this.animales.size();
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Variedades: " + getNroAnimales();
    }
}