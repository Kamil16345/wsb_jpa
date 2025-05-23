package com.jpacourse.mapper;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.persistance.entity.DoctorEntity;

public final class DoctorMapper {
    public static DoctorTO mapToTO(DoctorEntity entity) {
        if (entity == null) {
            return null;
        }

        DoctorTO doctorTO = new DoctorTO();
        doctorTO.setId(entity.getId());
        doctorTO.setFirstName(entity.getFirstName());
        doctorTO.setLastName(entity.getLastName());
        doctorTO.setTelephoneNumber(entity.getTelephoneNumber());
        doctorTO.setEmail(entity.getEmail());
        doctorTO.setDoctorNumber(entity.getDoctorNumber());
        doctorTO.setSpecialization(entity.getSpecialization());
        doctorTO.setAddress(entity.getAddress());

        return doctorTO;
    }

    public static DoctorEntity mapToEntity(DoctorTO to) {
        if (to == null) {
            return null;
        }

        DoctorEntity entity = new DoctorEntity();
        entity.setId(to.getId());
        entity.setFirstName(to.getFirstName());
        entity.setLastName(to.getLastName());
        entity.setTelephoneNumber(to.getTelephoneNumber());
        entity.setEmail(to.getEmail());
        entity.setDoctorNumber(to.getDoctorNumber());
        entity.setSpecialization(to.getSpecialization());
        entity.setAddress(to.getAddress());

        return entity;
    }
}
