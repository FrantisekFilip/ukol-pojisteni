package cz.itnetwork.ukol.mapper;

import cz.itnetwork.ukol.dto.AddressDTO;
import cz.itnetwork.ukol.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO toDto(Address address) {
        if (address == null) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setPostcode(address.getPostcode());

        return addressDTO;
    }

    public Address toEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }

        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setPostcode(addressDTO.getPostcode());

        return address;
    }
}
