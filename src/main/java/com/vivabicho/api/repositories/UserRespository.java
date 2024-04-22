package com.vivabicho.api.repositories;


import com.vivabicho.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {

    User findByName(String username);

    @Query("SELECT u FROM User u JOIN FETCH u.roles where u.name = :username")
    User findByUsernameFetchRoles(@Param("username") String username);
}
