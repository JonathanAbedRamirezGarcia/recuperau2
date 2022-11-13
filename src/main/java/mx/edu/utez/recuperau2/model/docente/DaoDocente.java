package mx.edu.utez.recuperau2.model.docente;

import mx.edu.utez.recuperau2.model.Repository;
import mx.edu.utez.recuperau2.model.estudiante.BeanEstudiante;
import mx.edu.utez.recuperau2.model.estudiante.DaoEstudiante;
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

public class DaoDocente implements Repository<BeanDocente> {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MySQLConnection client = new MySQLConnection();

    @Override
    public List<BeanDocente> findAll() {
        List<BeanDocente> docentes = new ArrayList<>();
        BeanDocente docente = null;
        try {
            conn = client.getConnection();
            String query = "SELECT * FROM docentes;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                docente = new BeanDocente();
                docente.setCurp(rs.getString("curp"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellidos(rs.getString("apellidos"));
                docente.setFechaNacimiento(rs.getString("fechaNac"));
                docente.setNumeroEmpleado(rs.getInt("numero_empleado"));
                docentes.add(docente);
            }
        } catch (SQLException e){
            Logger.getLogger(DaoDocente.class.getName())
                    .log(Level.SEVERE, "Error -> findAll"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }

        return docentes;
    }

    @Override
    public BeanDocente findById(Long id) {
        return null;
    }

    @Override
    public Response<BeanDocente> save(BeanDocente docente) {
        try {
            DaoFunciones daoFunciones = new DaoFunciones();

            if (daoFunciones.verificarDocenteCurp(docente.getCurp())){
                return new Response<BeanDocente>(403, "La CURP ya existe", docente, true);
            }

            if (daoFunciones.verificarNumeroEmpleado(docente.getNumeroEmpleado())){
                return new Response<BeanDocente>(403, "El número de empleado ya existe", docente, true);
            }

            conn = client.getConnection();

            String query = "INSERT INTO docentes VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, docente.getCurp());
            ps.setString(2, docente.getNombre());
            ps.setString(3, docente.getApellidos());
            ps.setString(4, docente.getFechaNacimiento());
            ps.setInt(5, docente.getNumeroEmpleado());
            if(ps.executeUpdate() == 1){
                return new Response<BeanDocente>(200, "Registrado correctamente", docente, false);
            } else {
                return new Response<>(200, "Error al registrar", docente, true);
            }

        } catch (SQLException e){
            Logger.getLogger(DaoDocente.class.getName())
                    .log(Level.SEVERE, "Error -> save: "+ e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        } finally {
            client.close(conn,ps,rs);
        }
    }

    @Override
    public Response<BeanDocente> update(BeanDocente docente) {
        Response<BeanEstudiante> response = new Response<>();
        try {
            conn = client.getConnection();
            String query = "UPDATE docentes set nombre =?,apellidos = ?, fechaNac = ?,numero_empleado = ? WHERE curp = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1,docente.getNombre());
            ps.setString(2,docente.getApellidos());
            ps.setString(3,docente.getFechaNacimiento());
            ps.setInt(4,docente.getNumeroEmpleado());
            ps.setString(5,docente.getCurp());
            if(ps.executeUpdate() == 1){
                return new Response<BeanDocente>(200, "Actualizado correctamente", docente, false);
            } else {
                return new Response<>(200, "Error al actualizar", docente, true);
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
    public Response<BeanDocente> remove(Long id) {
        return null;
    }

}
