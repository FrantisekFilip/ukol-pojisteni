package cz.itnetwork.ukol.controllers;

import cz.itnetwork.ukol.dto.InsuranceDTO;
import cz.itnetwork.ukol.dto.UserDTO;
import cz.itnetwork.ukol.entity.User;
import cz.itnetwork.ukol.mapper.UserMapper;
import cz.itnetwork.ukol.service.InsuranceService;
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

    @Autowired
    InsuranceService insuranceService;

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
        UserDTO userDTO = userMapper.toDtoWithInsurances(user);
        List<InsuranceDTO> insuranceDTOS = insuranceService.getAllInsurances();
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("insuranceDTOS", insuranceDTOS);
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
        userService.createUser(userMapper.toEntity(userDTO));
        return "redirect:get-all";
    }

    @PostMapping("update-user")
    public String updateUser(
            @ModelAttribute UserDTO userDTO,
            @RequestParam(name = "insurances", required = false) List<Long> insuranceIds
    ) {
        userDTO.setInsuranceIds(insuranceIds);
        userService.updateUserById(userDTO);
        //return "redirect:get-all";
        return "redirect:get-user/" + userDTO.getId();
    }
}
