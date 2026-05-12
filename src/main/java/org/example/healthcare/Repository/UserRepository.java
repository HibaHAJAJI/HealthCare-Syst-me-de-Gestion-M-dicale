package org.example.healthcare.Repository;

import org.example.healthcare.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username );
}
