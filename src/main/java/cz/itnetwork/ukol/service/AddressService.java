package cz.itnetwork.ukol.service;

import cz.itnetwork.ukol.dto.AddressDTO;
import cz.itnetwork.ukol.entity.Address;
import cz.itnetwork.ukol.entity.User;
import cz.itnetwork.ukol.mapper.AddressMapper;
import cz.itnetwork.ukol.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressMapper addressMapper;

    public Address getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public Address getNewAddress(Address address) {
        return getAddressById(addressRepository.save(address));
    }

    public void updateAddress(AddressDTO addressDTO) {
        addressRepository.updateAddress(addressMapper.toEntity(addressDTO));
    }
}
