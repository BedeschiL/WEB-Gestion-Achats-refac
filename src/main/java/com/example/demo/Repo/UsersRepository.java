package com.example.demo.Repo;

import com.example.demo.Models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface UsersRepository extends CrudRepository<Users, Long> {

    List<Users> findByEmail(String email);

    // custom query example and return a stream
    @Query("select c from Users c where c.email = :email")
    Stream<Users> findByEmailReturnStream(@Param("email") String email);

    Users findClientByUsername(String remoteUser);
}