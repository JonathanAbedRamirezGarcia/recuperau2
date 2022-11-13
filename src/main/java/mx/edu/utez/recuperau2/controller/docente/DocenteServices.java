package mx.edu.utez.recuperau2.controller.docente;

import mx.edu.utez.recuperau2.model.docente.BeanDocente;
import mx.edu.utez.recuperau2.model.docente.DaoDocente;
import mx.edu.utez.recuperau2.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/docentes")
public class DocenteServices {
    Map<String, Object> response = new HashMap<>();

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanDocente> getAll(){
        return new DaoDocente().findAll();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> save(BeanDocente docente){
        Response<BeanDocente> result = new DaoDocente().save(docente);
        response.put("result", result);
        return response;
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> update(BeanDocente docente){
        Response<BeanDocente> result = new DaoDocente().update(docente);
        response.put("result", result);
        return response;
    }

}
