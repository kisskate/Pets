package org.example.Service;

import org.example.relationships.Pet;

import java.util.List;

public interface PetService {
    Pet savePet(Pet pet);
    void deletePetById(long id);
    void deletePet(Pet pet);
    void deleteAllPets();
    Pet updatePet(Pet pet);
    Pet getPetById(long id);
    List<Pet> getAllPets();
}
