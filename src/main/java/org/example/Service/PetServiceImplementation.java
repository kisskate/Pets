package org.example.Service;

import org.example.DAO.PetDAOImplementation;
import org.example.relationships.Pet;

import java.util.List;

public class PetServiceImplementation implements PetService{
    private final PetDAOImplementation petDAOImplementation;
    public PetServiceImplementation(PetDAOImplementation petDAOImplementation){
        this.petDAOImplementation = petDAOImplementation;
    }


    /**
     * @param pet
     * @return
     */
    @Override
    public Pet savePet(Pet pet) {
        return petDAOImplementation.save(pet);
    }

    /**
     * @param id
     */
    @Override
    public void deletePetById(long id) {
        petDAOImplementation.deleteById(id);
    }

    /**
     * @param pet
     */
    @Override
    public void deletePet(Pet pet) {
        petDAOImplementation.deleteByEntity(pet);
    }

    /**
     *
     */
    @Override
    public void deleteAllPets() {
        petDAOImplementation.deleteAll();
    }

    /**
     * @param pet
     * @return
     */
    @Override
    public Pet updatePet(Pet pet) {

        return petDAOImplementation.update(pet);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public Pet getPetById(long id) {
        return petDAOImplementation.getById(id);
    }

    /**
     * @return
     */
    @Override
    public List<Pet> getAllPets() {
        return petDAOImplementation.getAll();
    }
}
