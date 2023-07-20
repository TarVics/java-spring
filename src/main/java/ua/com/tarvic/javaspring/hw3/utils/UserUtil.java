package ua.com.tarvic.javaspring.hw3.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import ua.com.tarvic.javaspring.hw3.models.User;
import ua.com.tarvic.javaspring.hw3.models.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserUtil {

    public User convertDtoToEntity(UserDTO userDTO) {
        User user = new User();
        int id = userDTO.getId();
        if (id > 0) user.setId(id);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public UserDTO convertEntityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        if (user != null) {
            int id = user.getId();
            if (id > 0) userDTO.setId(id);
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
        }
        return userDTO;
    }

    public List<UserDTO> convertEntityToDto(List<User> users) {
        return users.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

}
