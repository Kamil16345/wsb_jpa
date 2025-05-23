package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.VisitEntity;

import java.util.List;

public interface VisitService {

    VisitTO findById(final Long id);

    List<VisitTO> findAll();

    List<VisitTO> findVisitsByPatientId(final Long patientId);

}
