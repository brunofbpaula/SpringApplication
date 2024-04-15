package com.blackout.university.business;

import com.blackout.university.exceptions.NotFoundResourceException;
import com.blackout.university.models.Address;
import com.blackout.university.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBusiness {

    @Autowired
    AddressRepository repository;

    public List<Address> searchForAllAdresses(){
        return repository.findAll();
    }

    public Address searchById(Long id) throws NotFoundResourceException {
        Optional<Address> address = repository.findById(id);

        if (address.isEmpty()) {
            throw new NotFoundResourceException("Cannot find address with ID: " + id);
        }

        return address.get();
    }
}
