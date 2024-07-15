package cz.itnetwork.ukol.controllers;

import cz.itnetwork.ukol.dto.UserDTO;
import cz.itnetwork.ukol.entity.User;
import cz.itnetwork.ukol.mapper.UserMapper;
import cz.itnetwork.ukol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("get-all")
    public String getAllUsers(
            Model model
    ) {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOS = new LinkedList<>();
        for (User user : users) {
            userDTOS.add(userMapper.toDto(user));
        }
        model.addAttribute("userDTOS", userDTOS);
        return "users";
    }

    @GetMapping("get-user/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        UserDTO userDTO = userMapper.toDto(user);
        model.addAttribute("userDTO", userDTO);
        return "user";
    }

    @GetMapping("create-user")
    public String getNewUserForm(
            @ModelAttribute UserDTO userDTO
    )
    {
        return "create-user";
    }

    @PostMapping("create-user")
    public String createUser(
            @ModelAttribute UserDTO userDTO
    ) {
        User user = userMapper.toEntity(userDTO);
        userDTO = userMapper.toDto(userService.getNewUser(user));
        return "user";
    }

    @PostMapping("update-user")
    public String updateUser(
            @ModelAttribute UserDTO userDTO
    ) {
        User user = userMapper.toEntity(userDTO);
        userDTO = userMapper.toDto(userService.getNewUser(user));
        return "user";
    }
}
