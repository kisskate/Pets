package org.example;

import org.example.DAO.PetDAOImplementation;
import org.example.Service.PetServiceImplementation;
import org.example.relationships.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceImplementationTest {
    private PetDAOImplementation petDAO;
    private PetServiceImplementation petService;

    @BeforeEach
    void setUp() {
        petDAO = Mockito.mock(PetDAOImplementation.class);
        petService = new PetServiceImplementation(petDAO);
    }

    @Test
    void testSavePet() {
        Pet pet = new Pet();
        when(petDAO.save(pet)).thenReturn(pet);

        Pet savedPet = petService.savePet(pet);
        assertNotNull(savedPet);
        verify(petDAO, times(1)).save(pet);
    }

    @Test
    void testGetPetById() {
        long id = 1L;
        Pet pet = new Pet();
        when(petDAO.getById(id)).thenReturn(pet);

        Pet foundPet = petService.getPetById(id);
        assertNotNull(foundPet);
        verify(petDAO, times(1)).getById(id);
    }

}
