package com.jpacourse.persistance.builders;

import com.jpacourse.persistance.entity.*;
import com.jpacourse.persistance.enums.Specialization;
import com.jpacourse.persistance.enums.TreatmentType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TestDataFactory {

    public static AddressEntity createDefaultAddress() {
        return TestAddressBuilder.defaultAddress().build();
    }

    public static PatientEntity createDefaultPatient() {
        return TestPatientBuilder.defaultPatient().build();
    }

    public static DoctorEntity createDefaultDoctor() {
        return TestDoctorBuilder.defaultDoctor().build();
    }

    public static VisitEntity createDefaultVisit() {
        return TestVisitBuilder.defaultVisit().build();
    }

    public static MedicalTreatmentEntity createDefaultMedicalTreatment() {
        return TestMedicalTreatmentBuilder.defaultMedicalTreatment().build();
    }

    public static PatientEntity createPatientWithVisits(int visitCount) {
        PatientEntity patient = TestPatientBuilder.defaultPatient().build();

        for (int i = 0; i < visitCount; i++) {
            VisitEntity visit = TestVisitBuilder.defaultVisit()
                    .withDescription("Wizyta " + (i + 1))
                    .withTime(LocalDateTime.now().plusDays(i + 1))
                    .build();
            visit.setPatient(patient);
            if (patient.getVisits() == null) {
                patient.setVisits(Arrays.asList(visit));
            } else {
                patient.getVisits().add(visit);
            }
        }

        return patient;
    }

    public static DoctorEntity createDoctorWithSpecialization(Specialization specialization) {
        return TestDoctorBuilder.defaultDoctor()
                .withSpecialization(specialization)
                .build();
    }

    public static VisitEntity createVisitWithTreatments(List<TreatmentType> treatmentTypes) {
        VisitEntity visit = TestVisitBuilder.defaultVisit().build();

        for (TreatmentType type : treatmentTypes) {
            MedicalTreatmentEntity treatment = TestMedicalTreatmentBuilder.defaultMedicalTreatment()
                    .withTreatmentType(type)
                    .build();
            visit.getMedicalTreatments().add(treatment);
        }

        return visit;
    }
}

