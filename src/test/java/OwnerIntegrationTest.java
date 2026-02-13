package org.example;
import org.example.DAO.OwnerDAOImplementation;
import org.example.relationships.Owner;
import org.example.util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class OwnerIntegrationTest {
    private static PostgreSQLContainer<?> postgresContainer;
    private static OwnerDAOImplementation ownerDAO;

    @BeforeAll
    static void setUp() {
        postgresContainer = new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("lab2new")
                .withUsername("postgres")
                .withPassword("password");
        postgresContainer.start();

        ownerDAO = new OwnerDAOImplementation(HibernateUtil.getSessionFactory(
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
    void testCRUDOperations() {
        Owner owner = new Owner();
        owner.setName("Никитка");
        owner.setBirthDate(LocalDate.of(2006, 5, 10));

        Owner savedOwner = ownerDAO.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());

        Owner foundOwner = ownerDAO.getById(savedOwner.getId());
        assertNotNull(foundOwner);
        assertEquals("Никитка", foundOwner.getName());

        foundOwner.setName("Роман");
        Owner updatedOwner = ownerDAO.update(foundOwner);
        assertEquals("Роман", updatedOwner.getName());


        ownerDAO.deleteById(updatedOwner.getId());
        Owner deletedOwner = ownerDAO.getById(updatedOwner.getId());
        assertNull(deletedOwner);
    }
}
