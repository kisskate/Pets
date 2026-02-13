package org.example.Service;

import org.example.relationships.Owner;

import java.util.List;

public interface OwnerService {
    Owner saveOwner(Owner owner);
    void deleteOwnerById(long id);
    void deleteOwner(Owner owner);
    void deleteAllOwners();
    Owner updateOwner(Owner owner);
    Owner getOwnerById(long id);
    List<Owner> getAllOwner();
}
