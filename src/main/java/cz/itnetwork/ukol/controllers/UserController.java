package cz.itnetwork.ukol.controllers;

import cz.itnetwork.ukol.dto.UserDTO;
import cz.itnetwork.ukol.entity.User;
import cz.itnetwork.ukol.mapper.UserMapper;
import cz.itnetwork.ukol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("get-all")
    public String getAllUsers() {
        return "users";
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
}
