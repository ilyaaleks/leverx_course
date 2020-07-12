package org.bstu.fit.service.impl;

import org.bstu.fit.converter.UserMapper;
import org.bstu.fit.dto.ImagePath;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.dto.UserPageDto;
import org.bstu.fit.model.User;
import org.bstu.fit.repository.UserRepository;
import org.bstu.fit.service.MailSender;
import org.bstu.fit.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:db.properties")
public class UserServiceImpl implements UserService {
    @Value("${upload.path}")
    private String uploadPath;

    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private MailSender mailSender;
    private Environment env;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, MailSender mailSender, Environment env, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
        this.env = env;
        this.userMapper = userMapper;
    }

    @Override
    public User register(UserDto userDto) {
        User user = UserMapper.INSTANCE.fromDTO(userDto);
        User existUser = userRepository.findByUsername(user.getUsername());
        if (existUser != null) {
            throw new IllegalArgumentException("User is exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivate(false);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPhotoUrl("default.jpg");
        if (StringUtils.isEmpty(user.getEmail())) {
            throw new IllegalArgumentException("Email is invalid");
        }
        String message = String.format("Hello to UWSR, %s!\n" +
                        "We are glad to see you. Please, visit the following link: " + env.getRequiredProperty("server.url") + "registration/activate/%s",
                user.getUsername(),
                user.getActivationCode());
        mailSender.send(user.getEmail(), "Activation Code", message);
        User registeredUser = userRepository.save(user);
        return registeredUser;
    }

    @Override
    public UserPageDto getPageableUser(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        if (users == null) {
            throw new IllegalArgumentException("Page parameters is invalid");
        }
        UserPageDto userPageDto = new UserPageDto();
        for (User user : users.getContent()) {
            UserDto userDto = UserMapper.INSTANCE.toDTO(user);
            userPageDto.addUser(userDto);
        }
        userPageDto.setTotalPage(users.getTotalPages());
        userPageDto.setCurrentPage(pageable.getPageNumber());
        return userPageDto;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return user;
    }

    @Override
    public User findById(long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("User with id not found");
        }
        return user;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(UserDto userDto) {
        if (!userDto.getUsername().equals(getUsernameOfCurrentUser())) {
            throw new IllegalArgumentException("Unable to update user");
        }
        User user = userRepository.findByUsername(userDto.getUsername());
        user.setLastName(userDto.getLastName());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        try {
            if (user == null) {
                throw new IllegalArgumentException("Invalid user activation code");
            }
            user.setActivationCode(null);
            user.setLastPasswordResetDate(new Date());
            user.setActivate(true);
            User activatedUser = userRepository.save(user);
            return activatedUser;
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Invalid user activation code", ex);
        }

    }

    @Override
    public ImagePath updatePhoto(MultipartFile file, String username) {
        User user = userRepository.findByUsername(username);
        try {

            if (file == null || file.getOriginalFilename().isEmpty()) {
                throw new IllegalArgumentException("Problems with updating photos");
            }
            else if(user == null || !user.getUsername().equals(getUsernameOfCurrentUser()))
            {
                throw new AccessException("Access denied");
            }
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/" + resultFilename));
                user.setPhotoUrl(resultFilename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Photo can't be updated", ex);
        }
        catch(AccessException ex)
        {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Access denied", ex);
        }


        return new ImagePath(userRepository.save(user).getPhotoUrl());
    }

    private String getUsernameOfCurrentUser() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
