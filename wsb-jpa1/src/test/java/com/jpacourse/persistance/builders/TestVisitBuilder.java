package com.jpacourse.persistance.builders;

import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestVisitBuilder {
    private VisitEntity visit = new VisitEntity();

    public TestVisitBuilder withDescription(String description) {
        visit.setDescription(description);
        return this;
    }

    public TestVisitBuilder withTime(LocalDateTime time) {
        visit.setTime(time);
        return this;
    }

    public TestVisitBuilder withDoctor(DoctorEntity doctor) {
        visit.setDoctor(doctor);
        return this;
    }

    public TestVisitBuilder withPatient(PatientEntity patient) {
        visit.setPatient(patient);
        return this;
    }

    public TestVisitBuilder withMedicalTreatments(List<MedicalTreatmentEntity> treatments) {
        visit.setMedicalTreatments(treatments);
        return this;
    }

    public VisitEntity build() {
        return visit;
    }

    public static TestVisitBuilder defaultVisit() {
        return new TestVisitBuilder()
                .withDescription("Badanie kontrolne")
                .withTime(LocalDateTime.now().plusDays(7))
//                .withDoctor(TestDoctorBuilder.defaultDoctor().build())
//                .withPatient(TestPatientBuilder.defaultPatient().build())
                .withMedicalTreatments(new ArrayList<>());
    }
}

