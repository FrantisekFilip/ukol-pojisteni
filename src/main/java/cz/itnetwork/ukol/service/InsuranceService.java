package cz.itnetwork.ukol.service;

import cz.itnetwork.ukol.dto.InsuranceDTO;
import cz.itnetwork.ukol.entity.Insurance;
import cz.itnetwork.ukol.mapper.InsuranceMapper;
import cz.itnetwork.ukol.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    InsuranceMapper insuranceMapper;

    public List<InsuranceDTO> getAllInsurances() {
        List<Insurance> insurances = insuranceRepository.findAll();
        List<InsuranceDTO> insuranceDTOS = new LinkedList<>();
        for (Insurance insurance : insurances){
            insuranceDTOS.add(insuranceMapper.toDto(insurance));
        }
        return insuranceDTOS;
    }

    public void saveNewInsurance(InsuranceDTO insuranceDTO){
        insuranceRepository.saveNewInsurance(insuranceMapper.toEntity(insuranceDTO));
    }
}
