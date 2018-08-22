package com.deviceproblem.help.repository;

import com.deviceproblem.help.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
User findOneByEmail(String email);
}
