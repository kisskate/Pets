package org.example.DAO;

import org.example.relationships.Pet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PetDAOImplementation implements GenericDAO<Pet>{
    private final SessionFactory sessionFactory;

    public PetDAOImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * для сохранения питомцев
     * @param pet
     * @return
     */
    @Override
    public Pet save(Pet pet) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(pet);
            transaction.commit();
            session.close();
            return pet;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Unable to save pet", e);
        }

    }

    /**
     * @param id
     */
    @Override
    public void deleteById(long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Pet pet = session.get(Pet.class, id);
            session.delete(pet);
            transaction.commit();
        }
        catch(Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Unable to delete pet by Id", e);
        }
    }

    /**
     * @param pet
     */
    @Override
    public void deleteByEntity(Pet pet) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(pet);
            transaction.commit();
        }
        catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            throw new RuntimeException("Unable to delete pet", e);
        }
    }

    /**
     *
     */
    @Override
    public void deleteAll() {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Pet").executeUpdate();
            transaction.commit();
        }
        catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            throw new RuntimeException("Unable to delete all pets", e);
        }
    }

    /**
     * @param pet
     * @return
     */
    @Override
    public Pet update(Pet pet) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.update(pet);
            transaction.commit();
            return pet;
        }
        catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            throw new RuntimeException("Unable to update Pet", e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Pet getById(long id) {
        //Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            //transaction = session.beginTransaction();
            return session.get(Pet.class, id);
        }
        catch(Exception e){
            throw new RuntimeException("Unable to get pet by id");
        }
    }

    /**
     * @return
     */
    @Override
    public List<Pet> getAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Pet", Pet.class).list();
        }
        catch(Exception e){
            throw new RuntimeException("Unable to get all pets", e);
        }
    }
}
