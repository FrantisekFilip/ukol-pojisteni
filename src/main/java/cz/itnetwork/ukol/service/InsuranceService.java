package cz.itnetwork.ukol.service;

import cz.itnetwork.ukol.dto.InsuranceDTO;
import cz.itnetwork.ukol.entity.Insurance;
import cz.itnetwork.ukol.mapper.InsuranceMapper;
import cz.itnetwork.ukol.repository.InsuranceRepository;
import cz.itnetwork.ukol.repository.UserInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceService {

    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    InsuranceMapper insuranceMapper;

    @Autowired
    UserInsuranceRepository userInsuranceRepository;

    public List<InsuranceDTO> getAllInsurances() {
        List<Insurance> insurances = insuranceRepository.findAll();
        List<InsuranceDTO> insuranceDTOS = new LinkedList<>();
        for (Insurance insurance : insurances){
            insuranceDTOS.add(insuranceMapper.toDto(insurance));
        }
        return insuranceDTOS;
    }

    public List<InsuranceDTO> getInsurancesByUserId(Long userId) {
        List<Insurance> insurances = insuranceRepository.findInsurancesByUserId(userId);
        return insurances.stream()
                .map(insuranceMapper::toDto)
                .collect(Collectors.toList());
    }

    public void saveNewInsurance(InsuranceDTO insuranceDTO){
        insuranceRepository.saveNewInsurance(insuranceMapper.toEntity(insuranceDTO));
    }

    public void deleteUserInsurance(Long userId, Long insuranceId){
        userInsuranceRepository.deleteUserInsurance(userId, insuranceId);
    }
}
