package com.jpacourse.dto;

import com.jpacourse.persistance.enums.TreatmentType;

import java.io.Serializable;

public class MedicalTreatmentTO implements Serializable {
    private TreatmentType treatmentType;
    private String description;

    public TreatmentType getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(TreatmentType treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
