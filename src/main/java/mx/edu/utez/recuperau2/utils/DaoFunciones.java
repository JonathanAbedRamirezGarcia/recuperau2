package mx.edu.utez.recuperau2.utils;

import mx.edu.utez.recuperau2.model.docente.DaoDocente;
import mx.edu.utez.recuperau2.model.estudiante.DaoEstudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoFunciones {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MySQLConnection client = new MySQLConnection();

    public boolean verificarDocenteCurp(String curp){
        try {
            conn = client.getConnection();
            String query = "SELECT * FROM docentes WHERE curp = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1,curp);

            rs = ps.executeQuery();

            if(rs.next()){
                if (rs.getString("curp").equals(curp)){
                    return true;
                }
            }

        } catch (SQLException e){
            Logger.getLogger(DaoDocente.class.getName())
                    .log(Level.SEVERE, "Error -> verificarCurp"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return false;
    }

    public boolean verificarNumeroEmpleado(int numeroEmpleado){
        try {
            conn = client.getConnection();
            String query = "SELECT * FROM docentes WHERE numero_empleado = ?;";
            ps = conn.prepareStatement(query);
            ps.setInt(1,numeroEmpleado);

            rs = ps.executeQuery();

            if(rs.next()){
                if (rs.getInt("numero_empleado") == numeroEmpleado){
                    return true;
                }
            }

        } catch (SQLException e){
            Logger.getLogger(DaoDocente.class.getName())
                    .log(Level.SEVERE, "Error -> verificar Numero de Empleado"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return false;
    }

    public boolean verificarEstudianteCurp(String curp){
        try {
            conn = client.getConnection();
            String query = "SELECT * FROM estudiantes WHERE curp = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1,curp);

            rs = ps.executeQuery();

            if(rs.next()){
                return true;
            }

        } catch (SQLException e){
            Logger.getLogger(DaoEstudiante.class.getName())
                    .log(Level.SEVERE, "Error -> verifyStudent"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return false;
    }

    public boolean verificarMatricula(String matricula){
        try {
            conn = client.getConnection();
            String query = "SELECT * FROM estudiantes WHERE matricula = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1,matricula);

            rs = ps.executeQuery();

            if(rs.next()){
                return true;
            }

        } catch (SQLException e){
            Logger.getLogger(DaoEstudiante.class.getName())
                    .log(Level.SEVERE, "Error -> verifyStudent"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return false;
    }


}
