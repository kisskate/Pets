package org.example.Service;

import org.example.DAO.OwnerDAOImplementation;
import org.example.relationships.Owner;

import java.util.List;

public class OwnerServiceImplementation implements OwnerService{
    private final OwnerDAOImplementation ownerDAOImplementation;
    public OwnerServiceImplementation(OwnerDAOImplementation ownerDAOImplementation){
        this.ownerDAOImplementation = ownerDAOImplementation;
    }
    /**
     * @param owner
     * @return
     */
    @Override
    public Owner saveOwner(Owner owner) {
        return ownerDAOImplementation.save(owner);
    }

    /**
     * @param id
     */
    @Override
    public void deleteOwnerById(long id) {
        ownerDAOImplementation.deleteById(id);
    }

    /**
     * @param owner
     */
    @Override
    public void deleteOwner(Owner owner) {
        ownerDAOImplementation.deleteByEntity(owner);
    }

    /**
     *
     */
    @Override
    public void deleteAllOwners() {
        ownerDAOImplementation.deleteAll();
    }

    /**
     * @param owner
     * @return
     */
    @Override
    public Owner updateOwner(Owner owner) {
        return ownerDAOImplementation.update(owner);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Owner getOwnerById(long id) {
        return  ownerDAOImplementation.getById(id);
    }

    /**
     * @return
     */
    @Override
    public List<Owner> getAllOwner() {
        return ownerDAOImplementation.getAll();
    }
}
