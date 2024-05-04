package swe.group2.springbootjacocogradle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import swe.group2.springbootjacocogradle.model.Users;

import java.util.List;

@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findByNameContaining(String name);

}
