package com.jpacourse.service.impl;

import com.jpacourse.dto.AddressTO;
import com.jpacourse.mapper.AddressMapper;
import com.jpacourse.persistance.dao.AddressDao;
import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao pAddressDao) {
        addressDao = pAddressDao;
    }

    @Override
    public List<AddressTO> findAll() {
        return addressDao.findAll().stream()
                .map(AddressMapper::mapToTO)
                .toList();
    }

    @Override
    public AddressTO findById(Long id) {
        final AddressEntity entity = addressDao.findOne(id);
        return AddressMapper.mapToTO(entity);
    }

    @Override
    public void deleteById(Long id) {
        addressDao.delete(id);
    }

    @Override
    public AddressTO save(AddressTO addressTO) {
        AddressEntity savedAddress = addressDao.save(AddressMapper.mapToEntity(addressTO));
        return AddressMapper.mapToTO(savedAddress);
    }

    @Override
    public AddressTO update(AddressTO addressTO) {
        AddressEntity updatedUser = addressDao.update(AddressMapper.mapToEntity(addressTO));
        return AddressMapper.mapToTO(updatedUser);
    }

}
