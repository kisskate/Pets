package org.example.Program;

import org.example.relationships.Owner;
import org.example.relationships.Pet;
import org.example.relationships.PetColor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataInit {
    public static void initialize(SessionFactory sessionFactory) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Owner owner1 = new Owner("Иван", LocalDate.of(1980, 5, 15));
            Owner owner2 = new Owner("Мария", LocalDate.of(1975, 10, 20));

            Pet pet1 = new Pet("Барс", LocalDate.of(2018, 3, 10), "Персидская", PetColor.GREY);
            Pet pet2 = new Pet("Мур", LocalDate.of(2019, 7, 22), "Сиамская", PetColor.WHITE);
            Pet pet3 = new Pet("Рыжик", LocalDate.of(2020, 1, 5), "Британская", PetColor.GINGER);

            owner1.setPets(new ArrayList<>());
            owner1.getPets().add(pet1);
            owner1.getPets().add(pet2);
            owner2.setPets(new ArrayList<>());
            owner2.getPets().add(pet3);

            pet1.setOwner(owner1);
            pet2.setOwner(owner1);
            pet3.setOwner(owner2);

            pet1.setFriends(new ArrayList<>());
            pet2.setFriends(new ArrayList<>());
            pet1.addFriend(pet2);

            session.persist(owner1);
            session.persist(owner2);
            session.persist(pet1);
            session.persist(pet2);
            session.persist(pet3);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}