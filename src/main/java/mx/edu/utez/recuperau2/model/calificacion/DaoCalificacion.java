package mx.edu.utez.recuperau2.model.calificacion;

import mx.edu.utez.recuperau2.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoCalificacion {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MySQLConnection client = new MySQLConnection();

    public double findAverage() {
        double average = 0;
        try {
            conn = client.getConnection();
            String query = "SELECT AVG(calificacion) as promedio FROM calificaciones;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                average += rs.getDouble("promedio");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close(conn, ps, rs);
        }
        return average;
        }

     }
