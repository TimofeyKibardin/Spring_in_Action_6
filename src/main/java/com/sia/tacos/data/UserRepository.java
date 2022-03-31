package com.sia.tacos.data;

import com.sia.tacos.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository {

    User findByUsername(String username);

}
