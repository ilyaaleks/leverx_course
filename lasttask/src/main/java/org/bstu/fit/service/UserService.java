package org.bstu.fit.service;

import org.bstu.fit.dto.ImagePath;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.dto.UserPageDto;
import org.bstu.fit.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User register(UserDto user);
    UserPageDto getPageableUser(Pageable pageable);
    User findByUsername(String username);
    User findById(long id);
    void delete(long id);
    User updateUser(UserDto user);
    User activateUser(String code);
    ImagePath updatePhoto(MultipartFile file,String username);
    User findByEmail(String email);
}
