package org.example;
import org.example.DAO.PetDAOImplementation;
import org.example.relationships.Pet;
import org.example.relationships.PetColor;
import org.example.util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class PetIntegrationTest {
    private static PostgreSQLContainer<?> postgresContainer;
    static PetDAOImplementation petDAO;

    @BeforeAll
    static void setUp() {
        postgresContainer = new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("lab2new")
                .withUsername("postgres")
                .withPassword("password");
        postgresContainer.start();
        petDAO = new PetDAOImplementation(HibernateUtil.getSessionFactory(
                postgresContainer.getJdbcUrl(),
                postgresContainer.getUsername(),
                postgresContainer.getPassword()
        ));
    }

    @AfterAll
    static void tearDown() {
        postgresContainer.stop();
    }
    @Test
    void testCRUD() {
        Pet pet = new Pet();
        pet.setName("Пушок");
        pet.setBirthDate(LocalDate.of(2015, 1, 21));
        pet.setColor(PetColor.GINGER);

        Pet savedPet = petDAO.save(pet);
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());


        Pet readPet = petDAO.getById(savedPet.getId());
        assertNotNull(readPet);
        assertEquals("Пушок", readPet.getName());

        readPet.setName("Пушенька");
        Pet updatedPet = petDAO.update(readPet);
        assertEquals("Пушенька", updatedPet.getName());

        petDAO.deleteById(updatedPet.getId());
        Pet deletedPet = petDAO.getById(updatedPet.getId());
        assertNull(deletedPet);
    }
}
