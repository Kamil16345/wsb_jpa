package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    List<PatientEntity> findByLastName(String lastname);

    List<PatientEntity> findByEmail(String email);

    List<PatientEntity> findByVisitDate(Long patientId, LocalDate visitDate);

    List<PatientEntity> findVisitsWithDoctor(Long patientId, Long doctorId);

    long countEntities();

    List<PatientEntity> findPatientsWithUpcomingVisits(Long patientId);

    List<PatientEntity> findPatientsWithMoreVisitsThan(int visitsAmount);

    List<PatientEntity> findPatientsWhoJoinedOnGivenDate(LocalDate dateOfJoin);

    PatientEntity createVisitForPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String visitDescription);
}
