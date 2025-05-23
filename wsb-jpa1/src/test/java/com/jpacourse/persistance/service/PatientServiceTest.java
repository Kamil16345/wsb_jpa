package com.jpacourse.persistance.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistance.builders.TestDoctorBuilder;
import com.jpacourse.persistance.builders.TestPatientBuilder;
import com.jpacourse.persistance.builders.TestVisitBuilder;
import com.jpacourse.persistance.dao.AddressDao;
import com.jpacourse.persistance.dao.DoctorDao;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.dao.VisitDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.DoctorService;
import com.jpacourse.service.PatientService;
import com.jpacourse.service.VisitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;
    @Autowired
    private VisitService visitService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private VisitDao visitDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private AddressDao addressDao;

    @Transactional
    @Test
    public void shouldDeletePatientAndAssociatedVisitsButPreserveDoctors() {
        //given
        VisitEntity testVisit1 = TestVisitBuilder.defaultVisit().build();
        VisitEntity testVisit2 = TestVisitBuilder.defaultVisit().build();
        PatientEntity testPatient = TestPatientBuilder.defaultPatient()
                .withVisits(Arrays.asList(testVisit1, testVisit2))
                .build();
        DoctorEntity testDoctor = TestDoctorBuilder.defaultDoctor().build();
        //when
        addressDao.save(testPatient.getAddress());
        patientDao.save(testPatient);
        visitDao.save(testVisit1);
        visitDao.save(testVisit2);
        addressDao.save(testDoctor.getAddress());
        doctorDao.save(testDoctor);

        patientService.deleteById(testPatient.getId());

        //then
        assertThat(visitService.findVisitsByPatientId(testPatient.getId()).size()).isEqualTo(0);
        assertThat(doctorService.findById(testDoctor.getId())).isNotNull();
    }

    @Test
    @Transactional
    public void shouldReturnApprovedPatientToStructure() {
        //given
        VisitEntity testVisit1 = TestVisitBuilder.defaultVisit().build();
        VisitEntity testVisit2 = TestVisitBuilder.defaultVisit().build();
        PatientEntity testPatient = TestPatientBuilder.defaultPatient()
                .withVisits(Arrays.asList(testVisit1, testVisit2))
                .build();
        //when
        addressDao.save(testPatient.getAddress());
        patientDao.save(testPatient);
        visitDao.save(testVisit1);
        visitDao.save(testVisit2);

        PatientTO patientTO = patientService.findById(testPatient.getId());

        //then
        assertThat(testPatient.getId()).isEqualTo(patientTO.getId());
        assertThat(testPatient.getFirstName()).isEqualTo(patientTO.getFirstName());
        assertThat(testPatient.getLastName()).isEqualTo(patientTO.getLastName());
        assertThat(testPatient.getTelephoneNumber()).isEqualTo(patientTO.getTelephoneNumber());
        assertThat(testPatient.getEmail()).isEqualTo(patientTO.getEmail());
        assertThat(testPatient.getPatientNumber()).isEqualTo(patientTO.getPatientNumber());
        assertThat(testPatient.getDateOfBirth()).isEqualTo(patientTO.getDateOfBirth());
        assertThat(testPatient.getDateOfJoin()).isEqualTo(patientTO.getDateOfJoin());
        assertThat(testPatient.getVisits().size()).isEqualTo(2);
        assertThat(patientTO.getVisitEntities().size()).isEqualTo(0);
    }

}
