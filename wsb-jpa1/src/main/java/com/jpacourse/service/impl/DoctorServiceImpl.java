package com.jpacourse.service.impl;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.mapper.DoctorMapper;
import com.jpacourse.persistance.dao.DoctorDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.service.DoctorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;

    @Autowired
    public DoctorServiceImpl(DoctorDao doctor) {
        doctorDao = doctor;
    }

    @Override
    public DoctorTO findById(Long id) {
        DoctorEntity doctor = doctorDao.findOne(id);
        return DoctorMapper.mapToTO(doctor);
    }

    @Override
    public List<DoctorEntity> findAll() {
        return doctorDao.findAll();
    }
}
