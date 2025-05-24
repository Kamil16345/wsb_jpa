package com.jpacourse.service;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.persistance.entity.DoctorEntity;

import java.util.List;

public interface DoctorService {

    DoctorTO findById(final Long id);

    List<DoctorEntity> findAll();

    void deleteById(Long id);

    DoctorTO update(DoctorTO doctorTO);

    DoctorTO save(DoctorTO doctorTO);
}
