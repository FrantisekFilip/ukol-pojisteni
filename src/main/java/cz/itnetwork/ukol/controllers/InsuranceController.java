package cz.itnetwork.ukol.controllers;

import cz.itnetwork.ukol.dto.UserDTO;
import cz.itnetwork.ukol.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/insurances")
public class InsuranceController {
    @GetMapping("get-all")
    public String getAllUsers(
            Model model
    ) {
//        List<User> users = userService.getAllUsers();
//        List<UserDTO> userDTOS = new LinkedList<>();
//        for (User user : users) {
//            userDTOS.add(userMapper.toDto(user));
//        }
//        model.addAttribute("userDTOS", userDTOS);
        return "insurances";
    }
}
