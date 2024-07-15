package cz.itnetwork.ukol.service;

import cz.itnetwork.ukol.entity.Address;
import cz.itnetwork.ukol.entity.User;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {

        return userRepository.findById(id);
    }

    public User getNewUser(User user) {
        Address address = addressService.getNewAddress(user.getAddress());
        user.setAddress_id(address.getId());
        User user1 = getUserById(userRepository.save(user));
        user1.setAddress(address);
        return user1;
    }
}
