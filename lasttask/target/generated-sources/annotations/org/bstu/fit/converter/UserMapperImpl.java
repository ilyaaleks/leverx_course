package org.bstu.fit.converter;

import javax.annotation.Generated;
import org.bstu.fit.dto.UserDto;
import org.bstu.fit.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-06T14:16:01+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        return userDto;
    }

    @Override
    public User fromDTO(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        return user;
    }
}
