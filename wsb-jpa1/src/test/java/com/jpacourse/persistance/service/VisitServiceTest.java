package com.jpacourse.persistance.service;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.builders.TestDoctorBuilder;
import com.jpacourse.persistance.builders.TestPatientBuilder;
import com.jpacourse.persistance.builders.TestVisitBuilder;
import com.jpacourse.persistance.dao.AddressDao;
import com.jpacourse.persistance.dao.DoctorDao;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.dao.VisitDao;
import com.jpacourse.persistance.entity.AddressEntity;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.VisitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;
    @Autowired
    private VisitDao visitDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private AddressDao addressDao;

    @Transactional
    @Test
    public void shouldFindTwoVisitsForTestPatient1UsingPatientId() {
        //given
        PatientEntity testPatient1 = TestPatientBuilder.defaultPatient().build();
        PatientEntity testPatient2 = TestPatientBuilder.defaultPatient()
                .withFirstName("John")
                .withLastName("Testowy")
                .build();
        DoctorEntity testDoctor = TestDoctorBuilder.defaultDoctor().build();

        addressDao.save(testPatient1.getAddress());
        addressDao.save(testPatient2.getAddress());
        addressDao.save(testDoctor.getAddress());
        patientDao.save(testPatient1);
        patientDao.save(testPatient2);
        doctorDao.save(testDoctor);

        VisitEntity visit1 = TestVisitBuilder.defaultVisit()
                .withPatient(testPatient1)
                .withDoctor(testDoctor)
                .build();
        VisitEntity visit2 = TestVisitBuilder.defaultVisit()
                .withPatient(testPatient2)
                .withDoctor(testDoctor)
                .withTime(LocalDateTime.of(2025, 5, 16, 12, 15, 0))
                .build();
        VisitEntity visit3 = TestVisitBuilder.defaultVisit()
                .withPatient(testPatient1)
                .withDoctor(testDoctor)
                .withTime(LocalDateTime.of(2025, 5, 17, 12, 15, 0))
                .build();

        visitDao.save(visit1);
        visitDao.save(visit2);
        visitDao.save(visit3);

        //when
        patientDao.findAll()
        List<VisitTO> visits = visitService.findVisitsByPatientId(testPatient1.getId());

        //then
        assertThat(visits).isNotNull();
        assertThat(visits.size()).isEqualTo(2);

    }

}
