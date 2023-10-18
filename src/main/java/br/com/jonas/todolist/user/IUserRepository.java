package br.com.jonas.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, UUID>{
    User findByUserName(String userName);
}
