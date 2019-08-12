package com.example.demo.server;

import com.example.demo.Shiro.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImpl extends JpaRepository<User,String > {
    User findUserByUsername(String username);
}
