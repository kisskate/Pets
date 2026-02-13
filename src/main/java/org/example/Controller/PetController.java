package org.example.Controller;

import org.example.Service.PetServiceImplementation;
import org.example.relationships.Owner;
import org.example.relationships.Pet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetController {
    private final PetServiceImplementation petservice;
    public PetController(PetServiceImplementation petservice){

        this.petservice = petservice;
    }
    @POST
    public Response createPet(Pet pet){
        Pet newPet = petservice.savePet(pet);
        return Response.status(Response.Status.CREATED).entity(newPet).build();
    }
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id){
        Pet pet = petservice.getPetById(id);
        return Response.ok().build();
    }
    @GET
    public Response getAllPets(){
        List<Pet> petList = petservice.getAllPets();
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, Pet pet){
        pet.setId(id);
        Pet newPet = petservice.updatePet(pet);
        return Response.ok(newPet).build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") long id){
        petservice.deletePetById(id);
        return Response.noContent().build();
    }
    @DELETE
    public Response delete(Pet pet){
        petservice.deletePet(pet);
        return Response.noContent().build();
    }
}
