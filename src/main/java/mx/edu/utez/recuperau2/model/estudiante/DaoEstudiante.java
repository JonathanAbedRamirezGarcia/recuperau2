package mx.edu.utez.recuperau2.model.estudiante;

import mx.edu.utez.recuperau2.model.Repository;
import mx.edu.utez.recuperau2.model.calificacion.BeanCalificacion;
import mx.edu.utez.recuperau2.utils.DaoFunciones;
import mx.edu.utez.recuperau2.utils.MySQLConnection;
import mx.edu.utez.recuperau2.utils.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoEstudiante implements Repository<BeanEstudiante> {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MySQLConnection client = new MySQLConnection();

    @Override
    public List<BeanEstudiante> findAll() {
        List<BeanEstudiante> estudiantes = new ArrayList<>();
        BeanEstudiante estudiante = null;
        try {
            conn = client.getConnection();
            String query = "SELECT * FROM estudiantes;";
            ps = conn.prepareStatement(query);
            rs= ps.executeQuery();
            while (rs.next()){
                estudiante = new BeanEstudiante();
                estudiante.setCurp(rs.getString("curp"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setFechaNacimiento(rs.getString("fechaNac"));
                estudiante.setMatricula(rs.getString("matricula"));
                estudiantes.add(estudiante);
            }
        } catch (SQLException e){
            Logger.getLogger(DaoEstudiante.class.getName())
                    .log(Level.SEVERE, "Error -> findAll"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }

        return estudiantes;
    }

    public List<BeanEstudiante> findStudentsGrade() {
        List<BeanEstudiante> estudiantes = new ArrayList<>();
        BeanEstudiante estudiante = null;
        BeanCalificacion calificacion = null;
        try {
            conn = client.getConnection();
            String query = "SELECT * FROM estudiantes e INNER JOIN calificaciones c on e.curp=c.curp_estudiante;";
            ps = conn.prepareStatement(query);
            rs= ps.executeQuery();
            while (rs.next()){
                estudiante = new BeanEstudiante();
                calificacion = new BeanCalificacion();

                estudiante.setCurp(rs.getString("curp"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setFechaNacimiento(rs.getString("fechaNac"));
                estudiante.setMatricula(rs.getString("matricula"));
                calificacion.setId(rs.getInt("id"));
                calificacion.setMateria(rs.getString("materia"));
                calificacion.setCalificacion(rs.getInt("calificacion"));
                estudiante.setCalificacion(calificacion);
                estudiantes.add(estudiante);
            }
        } catch (SQLException e){
            Logger.getLogger(DaoEstudiante.class.getName())
                    .log(Level.SEVERE, "Error -> findAll"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }

        return estudiantes;
    }

    @Override
    public BeanEstudiante findById(Long id) {
        return null;
    }

    @Override
    public Response<BeanEstudiante> save(BeanEstudiante estudiante) {
        Response<BeanEstudiante> response = new Response<>();
        try {
            DaoFunciones daoFunciones = new DaoFunciones();

            if (daoFunciones.verificarEstudianteCurp(estudiante.getCurp())){
                return new Response<BeanEstudiante>(403, "La CURP ya existe", estudiante, true);
            }

            if (daoFunciones.verificarMatricula(estudiante.getMatricula())){
                return new Response<BeanEstudiante>(403, "La matricula ya existe", estudiante, true);
            }

            conn = client.getConnection();
            String query = "INSERT INTO estudiantes (curp,nombre,apellidos,fechaNac,matricula, curp_docente) VALUES (?,?,?,?,?,?);";
            ps = conn.prepareStatement(query);
            ps.setString(1,estudiante.getCurp());
            ps.setString(2,estudiante.getNombre());
            ps.setString(3,estudiante.getApellidos());
            ps.setString(4,estudiante.getFechaNacimiento());
            ps.setString(5,estudiante.getMatricula());
            ps.setString(6,estudiante.getDocente().getCurp());
            if(ps.executeUpdate() == 1){
                return new Response<BeanEstudiante>(200, "Registrado correctamente", estudiante, false);
            } else {
                return new Response<>(200, "Error al registrar", estudiante, true);
            }
        } catch (SQLException e){
            Logger.getLogger(DaoEstudiante.class.getName())
                    .log(Level.SEVERE, "Error -> save"+ e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        } finally {
            client.close(conn,ps,rs);
        }
    }

    @Override
    public Response<BeanEstudiante> update(BeanEstudiante estudiante) {
        Response<BeanEstudiante> response = new Response<>();
        try {
            conn = client.getConnection();
            String query = "UPDATE estudiantes set nombre =?,apellidos = ?, fechaNac = ?,matricula = ? WHERE curp = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1,estudiante.getNombre());
            ps.setString(2,estudiante.getApellidos());
            ps.setString(3,estudiante.getFechaNacimiento());
            ps.setString(4,estudiante.getMatricula());
            ps.setString(5,estudiante.getCurp());
            if(ps.executeUpdate() == 1){
                return new Response<BeanEstudiante>(200, "Actualizado correctamente", estudiante, false);
            } else {
                return new Response<>(200, "Error al actualizar", estudiante, true);
            }
        } catch (SQLException e){
            Logger.getLogger(DaoEstudiante.class.getName())
                    .log(Level.SEVERE, "Error -> save"+ e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        } finally {
            client.close(conn,ps,rs);
        }
    }

    @Override
    public Response<BeanEstudiante> remove(Long id) {
        return null;
    }
}
