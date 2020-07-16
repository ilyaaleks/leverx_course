package org.bstu.fit.converter;

import javax.annotation.Generated;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.model.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-16T00:18:26+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setLastName( user.getLastName() );
        userDto.setName( user.getName() );
        userDto.setUsername( user.getUsername() );
        userDto.setEmail( user.getEmail() );
        userDto.setPhotoUrl( user.getPhotoUrl() );
        userDto.setActivate( user.isActivate() );
        userDto.setActivationCode( user.getActivationCode() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }

    @Override
    public User fromDTO(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setLastName( userDto.getLastName() );
        user.setName( userDto.getName() );
        user.setUsername( userDto.getUsername() );
        user.setPassword( userDto.getPassword() );
        user.setEmail( userDto.getEmail() );
        user.setPhotoUrl( userDto.getPhotoUrl() );
        user.setActivate( userDto.isActivate() );
        user.setActivationCode( userDto.getActivationCode() );

        return user;
    }
}
