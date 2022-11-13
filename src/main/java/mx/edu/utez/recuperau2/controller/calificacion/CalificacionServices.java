package mx.edu.utez.recuperau2.controller.calificacion;

import mx.edu.utez.recuperau2.model.calificacion.DaoCalificacion;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@Path("/api/calificaciones")
public class CalificacionServices {
    Map<String, Double> response = new HashMap<>();

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public Map<String, Double> getAll(){
        double result = new DaoCalificacion().findAverage();
        response.put("promedio", result);
        return response;
    }
}
