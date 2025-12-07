public class Nota {
    private String materia;
    private double notaFinal;
    private Estudiante estudiante; 

    // Constructor
    public Nota(String materia, double notaFinal, Estudiante estudiante) {
        this.materia = materia;
        this.notaFinal = notaFinal;
        this.estudiante = estudiante;
    }

    // Getters y Setters
    public String getMateria() {
        return materia;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    // Método toString()
    @Override
    public String toString() {
        return "Nota [Materia=" + materia + ", Nota Final=" + notaFinal + ", Estudiante=" + estudiante.getNombre() + "]";
    }
}