package org.example;

import org.example.Controller.PetController;
import org.example.Service.PetServiceImplementation;
import org.example.relationships.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PetControllerTest {
    private PetServiceImplementation petService;
    private PetController petController;

    @BeforeEach
    void setUp() {
        petService = Mockito.mock(PetServiceImplementation.class);
        petController = new PetController(petService);
    }

    @Test
    void testCreatePet() {
        Pet pet = new Pet();
        when(petService.savePet(pet)).thenReturn(pet);

        Response response = petController.createPet(pet);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    void testGetPetById() {
        long id = 1L;
        Pet pet = new Pet();
        when(petService.getPetById(id)).thenReturn(pet);

        Response response = petController.getById(id);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
