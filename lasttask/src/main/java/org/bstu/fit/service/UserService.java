package org.bstu.fit.service;

import org.bstu.fit.dto.UserDto;
import org.bstu.fit.dto.UserPageDto;
import org.bstu.fit.model.User;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto register(UserDto user);
    UserPageDto getPageableUser(Pageable pageable);
    User findByUsername(String username);
    User findById(long id);
    void delete(long id);

    UserDto activateUser(String code);
}
