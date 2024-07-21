package cz.itnetwork.ukol.controllers;

import cz.itnetwork.ukol.dto.InsuranceDTO;
import cz.itnetwork.ukol.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/insurances")
public class InsuranceController {

    @Autowired
    InsuranceService insuranceService;

    @GetMapping("get-all")
    public String getAllInsurances(
            Model model
    ) {

        model.addAttribute("insuranceDTOS", insuranceService.getAllInsurances());
        return "insurances";
    }

    @GetMapping("create-insurance")
    public String getNewInsuranceForm(
            @ModelAttribute InsuranceDTO insuranceDTO
    )
    {
        return "create-insurance";
    }

    @PostMapping("create-insurance")
    public String getNewInsurance(
            @ModelAttribute InsuranceDTO insuranceDTO
    )
    {
        insuranceService.saveNewInsurance(insuranceDTO);
        return "redirect:get-all";
    }

    @PostMapping("/delete-insurance")
    public String deleteInsurance(@RequestParam Long insuranceId, @RequestParam Long userId) {
        insuranceService.deleteUserInsurance(userId,insuranceId);
        return "redirect:/users/get-user/" + userId;
    }
}
