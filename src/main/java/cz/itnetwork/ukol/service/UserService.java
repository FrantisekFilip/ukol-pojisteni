package cz.itnetwork.ukol.service;

import cz.itnetwork.ukol.dto.UserDTO;
import cz.itnetwork.ukol.entity.Address;
import cz.itnetwork.ukol.entity.User;
import cz.itnetwork.ukol.mapper.UserMapper;
import cz.itnetwork.ukol.repository.UserInsuranceRepository;
import cz.itnetwork.ukol.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserInsuranceRepository userInsuranceRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUserById(UserDTO userDTO) {
        addressService.updateAddress(userDTO.getAddressDTO());
        userRepository.updateUser(userMapper.toEntity(userDTO));

        if (userDTO.getInsuranceIds() != null) {
            userInsuranceRepository.updateUserInsurances(userDTO.getId(), userDTO.getInsuranceIds());
        }
    }
}
