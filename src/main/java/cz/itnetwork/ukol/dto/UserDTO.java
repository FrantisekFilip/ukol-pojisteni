package cz.itnetwork.ukol.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String firstName;

    private String lastName;

    private AddressDTO addressDTO;

    private List<Long> insuranceIds;

    private List<InsuranceDTO> insuranceDTOS;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }

    public List<Long> getInsuranceIds() {
        return insuranceIds;
    }

    public void setInsuranceIds(List<Long> insuranceIds) {
        this.insuranceIds = insuranceIds;
    }

    public List<InsuranceDTO> getInsuranceDTOS() {
        return insuranceDTOS;
    }

    public void setInsuranceDTOS(List<InsuranceDTO> insuranceDTOS) {
        this.insuranceDTOS = insuranceDTOS;
    }
}
