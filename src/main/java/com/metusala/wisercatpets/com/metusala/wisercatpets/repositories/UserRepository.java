package com.metusala.wisercatpets.com.metusala.wisercatpets.repositories;

import com.metusala.wisercatpets.com.metusala.wisercatpets.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}

