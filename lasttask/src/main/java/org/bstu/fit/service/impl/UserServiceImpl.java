package org.bstu.fit.service.impl;

import org.bstu.fit.converter.UserMapper;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.dto.UserPageDto;
import org.bstu.fit.model.User;
import org.bstu.fit.repository.UserRepository;
import org.bstu.fit.service.MailSender;
import org.bstu.fit.service.UserService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:db.properties")
public class UserServiceImpl implements UserService {

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
        User user=UserMapper.INSTANCE.fromDTO(userDto);
        User existUser=userRepository.findByUsername(user.getUsername());
        if(existUser!=null)
        {
            throw new IllegalArgumentException("User is exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivate(false);
        user.setActivationCode(UUID.randomUUID().toString());
        if(StringUtils.isEmpty(user.getEmail())){
            throw new IllegalArgumentException("Email is invalid");
        }
        String message=String.format("Hello to UWSR, %s!\n"+
                "We are glad to see you. Please, visit the following link: "+env.getRequiredProperty("server.url")+"registration/activate/%s",
                user.getUsername(),
                user.getActivationCode());
        mailSender.send(user.getEmail(),"Activation Code",message);
        User registeredUser=userRepository.save(user);
        return registeredUser;
    }

    @Override
    public UserPageDto getPageableUser(Pageable pageable) {
        Page<User> users=userRepository.findAll(pageable);
        if(users==null) {
            throw new IllegalArgumentException("Page parameters is invalid");
        }
        UserPageDto userPageDto=new UserPageDto();
        for(User user:users.getContent())
        {
            UserDto userDto= UserMapper.INSTANCE.toDTO(user);
            userPageDto.addUser(userDto);
        }
        userPageDto.setTotalPage(users.getTotalPages());
        userPageDto.setCurrentPage(pageable.getPageNumber());
        return userPageDto;
    }

    @Override
    public User findByUsername(String username) {
        User user=userRepository.findByUsername(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("Username not found");
        }
        return user;
    }

    @Override
    public User findById(long id) {
        User user=userRepository.findById(id);
        if(user==null)
        {
            throw new IllegalArgumentException("User with id not found");
        }
        return user;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User activateUser(String code) {
        User user=userRepository.findByActivationCode(code);
        if(user ==null)
        {
            throw new IllegalArgumentException("Invalid user activation code");
        }
        user.setActivationCode(null);
        user.setLastPasswordResetDate(new Date());
        user.setActivate(true);
        User activatedUser=userRepository.save(user);
        return activatedUser;
    }
}
