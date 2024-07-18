package cz.itnetwork.ukol.mapper;

import cz.itnetwork.ukol.dto.UserDTO;
import cz.itnetwork.ukol.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    AddressMapper addressMapper;

    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        if (user.getAddress() != null) {
            userDTO.setAddressDTO(addressMapper.toDto(user.getAddress()));
        }

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        if (userDTO.getAddressDTO() != null) {
            user.setAddress(addressMapper.toEntity(userDTO.getAddressDTO()));
        }

        return user;
    }
}

