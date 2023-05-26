package com.ega.banking.repository;

import com.ega.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String username);

    @Query("SELECT user FROM User user WHERE user.accountId = :accountId")
    User findUserByAccountId(long accountId);

    @Query("SELECT user FROM User user WHERE user.email = :email")
    User checkIfUserExist(String email);
}
