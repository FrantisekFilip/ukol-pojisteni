package cz.itnetwork.ukol.mapper;


import cz.itnetwork.ukol.dto.InsuranceDTO;
import cz.itnetwork.ukol.entity.Insurance;
import org.springframework.stereotype.Component;

@Component
public class InsuranceMapper {
    
    public InsuranceDTO toDto(Insurance insurance) {
        if(insurance == null){
            return null;
        }
        InsuranceDTO insuranceDTO = new InsuranceDTO();
        insuranceDTO.setId(insurance.getId());
        insuranceDTO.setType(insurance.getType());
        insuranceDTO.setDescription(insurance.getDescription());
        insuranceDTO.setAmount(insurance.getAmount());
        
        return insuranceDTO;
    }

    public Insurance toEntity(InsuranceDTO insuranceDTO) {
        if (insuranceDTO == null) {
            return null;
        }

        Insurance insurance = new Insurance();
        insurance.setId(insuranceDTO.getId());
        insurance.setType(insuranceDTO.getType());
        insurance.setDescription(insuranceDTO.getDescription());
        insurance.setAmount(insuranceDTO.getAmount());

        return insurance;
    }
}
