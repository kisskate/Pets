package org.example;

import org.example.relationships.Owner;
import org.example.relationships.Pet;
import org.example.relationships.PetColor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RelationshipTest {
    @Test
    void ownerPetRelationship_ShouldWorkCorrectly() {
        Owner owner = new Owner("John Doe", LocalDate.of(1980, 5, 15));
        Pet pet = new Pet("Fluffy", LocalDate.of(2020, 5, 15), "Persian", PetColor.WHITE);

        owner.addPet(pet);

        assertEquals(1, owner.getPets().size());
        assertEquals(owner, pet.getOwner());
    }

    @Test
    void petFriendship_ShouldBeBidirectional() {
        Pet pet1 = new Pet("Fluffy", LocalDate.of(2020, 5, 15), "Persian", PetColor.WHITE);
        Pet pet2 = new Pet("Whiskers", LocalDate.of(2019, 3, 10), "Siamese", PetColor.BLACK);

        pet1.addFriend(pet2);

        assertTrue(pet1.getFriends().contains(pet2));
        assertTrue(pet2.getFriends().contains(pet1));
    }
}