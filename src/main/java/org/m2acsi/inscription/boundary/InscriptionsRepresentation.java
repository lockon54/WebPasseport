package org.m2acsi.inscription.boundary;

import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.m2acsi.entity.Inscription;

@Stateless
@Path("inscriptions")
public class InscriptionsRepresentation {

    @Inject
    InscriptionsRessource ressource;

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findById(@PathParam("id") long id) {
        Inscription ins = this.ressource.findById(id);
        if (ins != null) {
            return Response.ok(ins).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Inscription> getAll() {
        return this.ressource.getAll();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        this.ressource.delete(id);
    }

    @POST
    public Response save(Inscription ins, @Context UriInfo uriInfo) {
        Inscription saved = this.ressource.save(ins);
        long id = saved.getId();
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + id).build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("{id}")
    public Inscription update(@PathParam("id") long id, Inscription ins) {
        ins.setId(id);
        return this.ressource.save(ins);
    }
}
