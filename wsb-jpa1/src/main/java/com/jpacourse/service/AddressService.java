package com.jpacourse.service;

import com.jpacourse.dto.AddressTO;

import java.util.List;

public interface AddressService {
    AddressTO findById(final Long id);

    void deleteById(Long id);

    AddressTO save(AddressTO addressTO);

    AddressTO update(AddressTO addressTO);

    List<AddressTO> findAll();
}
