package mx.edu.utez.recuperau2.model.calificacion;

public class BeanCalificacion {
    private int id;
    private String materia;
    private double calificacion;

    public BeanCalificacion() {
    }

    public BeanCalificacion(int id, String materia, double calificacion) {
        this.id = id;
        this.materia = materia;
        this.calificacion = calificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
}
