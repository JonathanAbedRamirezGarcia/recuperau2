package mx.edu.utez.recuperau2.controller.estudiante;

import mx.edu.utez.recuperau2.model.estudiante.BeanEstudiante;
import mx.edu.utez.recuperau2.model.estudiante.DaoEstudiante;
import mx.edu.utez.recuperau2.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/estudiantes")
public class EstudianteServices {
    Map<String, Object> response = new HashMap<>();

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanEstudiante> getAll(){
        return new DaoEstudiante().findAll();
    }

    @GET
    @Path("/calificaciones/")
    @Produces(value = {"application/json"})
    public List<BeanEstudiante> getStudentsGrade(){
        return new DaoEstudiante().findStudentsGrade();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> save(BeanEstudiante estudiante){
        Response<BeanEstudiante> result = new DaoEstudiante().save(estudiante);
        response.put("result", result);
        return response;
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> update(BeanEstudiante estudiante){
        Response<BeanEstudiante> result = new DaoEstudiante().update(estudiante);
        response.put("result", result);
        return response;
    }
}
