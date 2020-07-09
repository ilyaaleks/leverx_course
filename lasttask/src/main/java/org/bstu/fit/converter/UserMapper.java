package org.bstu.fit.converter;

import org.bstu.fit.dto.UserDto;
import org.bstu.fit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    UserDto toDTO(User user);
    User fromDTO(UserDto userDto);

}
