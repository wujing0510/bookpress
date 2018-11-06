package com.yikeo.bookpress.modules.sys.repository;

import com.yikeo.bookpress.modules.sys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
