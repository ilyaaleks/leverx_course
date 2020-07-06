package org.bstu.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageDto {
    private List<UserDto> users=new ArrayList<>();
    private int currentPage;
    private int totalPage;
    public void addUser(UserDto user)
    {
        users.add(user);
    }
}
