package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistance.entity.VisitEntity;

public final class VisitMapper {
    public static VisitTO mapToTO(final VisitEntity visitEntity){
        VisitTO visit = new VisitTO();
        if(visitEntity == null){
            return visit;
        }
        visit.setTime(visitEntity.getTime());
        visit.setDoctor(DoctorMapper.mapToTO(visitEntity.getDoctor()));
        visit.setMedicalTreatments(MedicalTreatmentMapper.mapToTO(visitEntity.getMedicalTreatments()));

        return visit;
    }
}
