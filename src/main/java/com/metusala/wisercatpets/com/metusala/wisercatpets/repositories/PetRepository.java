package com.metusala.wisercatpets.com.metusala.wisercatpets.repositories;

import com.metusala.wisercatpets.com.metusala.wisercatpets.models.Pet;
import com.metusala.wisercatpets.com.metusala.wisercatpets.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long> {
    List<Pet> findByUser(User user);

}

