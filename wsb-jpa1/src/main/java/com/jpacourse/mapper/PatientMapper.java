package com.jpacourse.mapper;


import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class PatientMapper {

    public static PatientTO mapToTO(final PatientEntity patientEntity) {
        if (patientEntity == null) {
            return null;
        }
        final PatientTO patientTo = new PatientTO();
        patientTo.setId(patientEntity.getId());
        patientTo.setFirstName(patientEntity.getFirstName());
        patientTo.setLastName(patientEntity.getLastName());
        patientTo.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTo.setEmail(patientEntity.getEmail());
        patientTo.setPatientNumber(patientEntity.getPatientNumber());
        patientTo.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTo.setAddressEntity(patientEntity.getAddress());
        List<VisitTO> pastVisits = patientEntity.getVisits().stream()
                .filter(visit -> visit.getTime().isBefore(LocalDateTime.now()))
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
        patientTo.setVisits(pastVisits);
        patientTo.setDateOfJoin(patientEntity.getDateOfJoin());
        return patientTo;
    }

    public static PatientEntity mapToEntity(final PatientTO patientTo) {
        if (patientTo == null) {
            return null;
        }
        List<VisitEntity> visitEntities = patientTo.getVisitEntities().stream()
                .map(VisitMapper::mapToEntity)
                .toList();
        final PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTo.getId());
        patientEntity.setFirstName(patientTo.getFirstName());
        patientEntity.setLastName(patientTo.getLastName());
        patientEntity.setTelephoneNumber(patientTo.getTelephoneNumber());
        patientEntity.setEmail(patientTo.getEmail());
        patientEntity.setPatientNumber(patientTo.getPatientNumber());
        patientEntity.setDateOfBirth(patientTo.getDateOfBirth());
        patientEntity.setAddress(patientTo.getAddressEntity());
        patientEntity.setVisits(visitEntities);
        patientEntity.setDateOfJoin(patientTo.getDateOfJoin());
        return patientEntity;
    }

    public static List<PatientTO> mapToTO(final List<PatientEntity> patientEntities) {
        List<PatientTO> result = new ArrayList<>();
        patientEntities.forEach(patient ->
                result.add(mapToTO(patient))
        );
        return result;
    }
}
