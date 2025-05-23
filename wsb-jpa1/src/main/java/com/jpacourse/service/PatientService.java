package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.entity.PatientEntity;

import java.time.LocalDate;
import java.util.List;

public interface PatientService {
    PatientTO save(final PatientTO patientTO);

    PatientTO findById(final Long id);

    List<PatientTO> findAll();

    PatientTO update(final PatientTO patientTO);

    void deleteById(final Long id);

    List<PatientTO> findPatientsWithMoreVisitsThan(int visitsAmount);

    List<PatientTO> findPatientsWhoJoinedOnGivenDate(LocalDate dateOfJoin);
}
