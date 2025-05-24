package com.jpacourse.mapper;

import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.persistance.entity.MedicalTreatmentEntity;

import java.util.ArrayList;
import java.util.List;

public class MedicalTreatmentMapper {

    public static MedicalTreatmentTO mapToTO(MedicalTreatmentEntity medicalTreatmentEntity) {
        MedicalTreatmentTO medicalTreatmentTO = new MedicalTreatmentTO();
        medicalTreatmentTO.setTreatmentType(medicalTreatmentEntity.getType());
        return medicalTreatmentTO;
    }

    public static List<MedicalTreatmentTO> mapToTO(List<MedicalTreatmentEntity> medicalTreatmentEntityList) {
        List<MedicalTreatmentTO> medicalTreatmentList = new ArrayList<>();
        for(MedicalTreatmentEntity medicalTreatment: medicalTreatmentEntityList) {
            MedicalTreatmentTO medicalTreatmentTO = new MedicalTreatmentTO();
            medicalTreatmentTO.setTreatmentType(medicalTreatment.getType());
            medicalTreatmentList.add(medicalTreatmentTO);
        }

        return medicalTreatmentList;
    }
    public static List<MedicalTreatmentEntity> mapToEntity(List<MedicalTreatmentTO> medicalTreatmentTO) {
        List<MedicalTreatmentEntity> medicalTreatmentEntityList = new ArrayList<>();
        for(MedicalTreatmentTO medicalTreatment: medicalTreatmentTO) {
            MedicalTreatmentEntity medicalTreatmentEntity = new MedicalTreatmentEntity();
            medicalTreatmentEntity.setType(medicalTreatment.getTreatmentType());
            medicalTreatmentEntityList.add(medicalTreatmentEntity);
        }

        return medicalTreatmentEntityList;
    }
}
