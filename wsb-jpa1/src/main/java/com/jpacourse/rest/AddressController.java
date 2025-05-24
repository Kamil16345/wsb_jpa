package com.jpacourse.rest;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressTO findById(@PathVariable final Long id) {
        final AddressTO address = addressService.findById(id);
        if (address != null) {
            return address;
        }
        throw new EntityNotFoundException(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressTO create(@RequestBody AddressTO addressTO) {
        return addressService.save(addressTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressTO update(@PathVariable Long id, @RequestBody AddressTO addressTO) {
        addressTO.setId(id);
        return addressService.update(addressTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        addressService.deleteById(id);
    }
}
