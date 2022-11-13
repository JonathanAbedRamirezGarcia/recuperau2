package mx.edu.utez.recuperau2.model.docente;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeanDocente {
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String curp;
    private int numeroEmpleado;

    public BeanDocente() {
    }

    public BeanDocente(String nombre, String apellidos, String fechaNacimiento, String curp, int numeroEmpleado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.curp = curp;
        this.numeroEmpleado = numeroEmpleado;
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

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }
}
