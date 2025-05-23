package com.jpacourse.persistance.builders;

import com.jpacourse.persistance.entity.MedicalTreatmentEntity;
import com.jpacourse.persistance.enums.TreatmentType;

public class TestMedicalTreatmentBuilder {
    private MedicalTreatmentEntity treatment = new MedicalTreatmentEntity();

//    public TestMedicalTreatmentBuilder withId(Long id) {
//        treatment.setId(id);
//        return this;
//    }

    public TestMedicalTreatmentBuilder withTreatmentType(TreatmentType treatmentType) {
        treatment.setType(treatmentType);
        return this;
    }

    public MedicalTreatmentEntity build() {
        return treatment;
    }

    public static TestMedicalTreatmentBuilder defaultMedicalTreatment() {
        return new TestMedicalTreatmentBuilder()
                .withTreatmentType(TreatmentType.RTG);
    }
}
