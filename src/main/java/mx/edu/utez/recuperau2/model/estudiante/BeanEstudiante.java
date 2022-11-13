package mx.edu.utez.recuperau2.model.estudiante;

import mx.edu.utez.recuperau2.model.calificacion.BeanCalificacion;
import mx.edu.utez.recuperau2.model.docente.BeanDocente;

public class BeanEstudiante {
    private String curp;
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String matricula;
    private BeanCalificacion calificacion;
    private BeanDocente docente;

    public BeanEstudiante() {
    }

    public BeanEstudiante(String nombre, String apellidos, String fechaNacimiento, String curp, String matricula, BeanCalificacion calificacion, BeanDocente docente) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.curp = curp;
        this.matricula = matricula;
        this.calificacion = calificacion;
        this.docente = docente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public BeanCalificacion getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BeanCalificacion calificacion) {
        this.calificacion = calificacion;
    }

    public BeanDocente getDocente() {
        return docente;
    }

    public void setDocente(BeanDocente docente) {
        this.docente = docente;
    }
}


