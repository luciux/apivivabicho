package com.vivabicho.api.DTO;

import com.vivabicho.api.models.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {

    private String name;
    private String email;

    public UserDTO convert(User user) {
        BeanUtils.copyProperties(user, this, "id", "password", "admin");
        return this;
    }

    public List<UserDTO> convertList(List<User> userList){
        UserDTO userDTO = new UserDTO();
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> {
            userDTOList.add(userDTO.convert(user));
        });
        return userDTOList;
    }
}
