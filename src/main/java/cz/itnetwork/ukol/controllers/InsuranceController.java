package cz.itnetwork.ukol.controllers;

import cz.itnetwork.ukol.dto.InsuranceDTO;
import cz.itnetwork.ukol.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
