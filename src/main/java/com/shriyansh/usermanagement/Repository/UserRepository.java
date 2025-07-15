package com.shriyansh.usermanagement.Repository;

import com.shriyansh.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

}
