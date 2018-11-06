package com.yikeo.bookpress.modules.sys.service;

import com.yikeo.bookpress.modules.sys.model.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void register(User user);

    Page<User> findAll(int page, int size);
}
