package jsonprocessing.services;

import org.springframework.stereotype.Service;

public class PersonService {

    private AddressService addressService;

    public PersonService(AddressService addressService) {
        this.addressService = addressService;
    }
}
