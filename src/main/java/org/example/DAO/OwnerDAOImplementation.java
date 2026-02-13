package org.example.DAO;

import org.example.relationships.Owner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class OwnerDAOImplementation implements GenericDAO<Owner> {
    private SessionFactory sessionFactory;

    public OwnerDAOImplementation(SessionFactory sessionFactory) {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            this.sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании SessionFactory", e);
        }
    }

    @Override
    public Owner save(Owner owner) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.save(owner);
            transaction.commit();
            return owner;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Unable to save owner", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteById(long id) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            Owner owner = session.get(Owner.class, id);
            if (owner != null) {
                session.delete(owner);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Unable to delete owner by Id", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteByEntity(Owner owner) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.delete(owner);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Unable to delete owner", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteAll() {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Pet").executeUpdate();
            session.createQuery("DELETE FROM Owner").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete all owners", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Owner update(Owner owner) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.update(owner);
            transaction.commit();
            return owner;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Unable to update owner", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Owner getById(long id) {
        Session session = sessionFactory.openSession();
        try{
            return session.get(Owner.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get owner by id", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Owner> getAll() {
        Session session = sessionFactory.openSession();
        try{
            return session.createQuery("FROM Owner", Owner.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Unable to get all owners", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}