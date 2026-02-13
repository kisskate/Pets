package org.example.Controller;
import org.example.Service.OwnerServiceImplementation;
import org.example.relationships.Owner;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OwnerController {
    private final OwnerServiceImplementation ownerservice;
    public OwnerController(OwnerServiceImplementation ownerservice){

        this.ownerservice = ownerservice;
    }
    @POST
    public Response createOwner(Owner owner){
        Owner newOwner = ownerservice.saveOwner(owner);
        return Response.status(Response.Status.CREATED).entity(newOwner).build();
    }
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id){
        Owner owner = ownerservice.getOwnerById(id);
        if (owner == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }
    @GET
    public Response getAllOwners(){
        List<Owner> ownersList = ownerservice.getAllOwner();
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, Owner owner){
        owner.setId(id);
        Owner newOwner = ownerservice.updateOwner(owner);
        return Response.ok(newOwner).build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") long id){
        ownerservice.deleteOwnerById(id);
        return Response.noContent().build();
    }
    @DELETE
    public Response delete(Owner owner){
        ownerservice.deleteOwner(owner);
        return Response.noContent().build();
    }
}
