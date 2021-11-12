package com.example.demo.repository;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByChatId(Long chatId);
    Optional<User> findByChatId(Long chatId);

}
