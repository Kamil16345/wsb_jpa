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
    public static VisitEntity mapToEntity(final VisitTO visitTO){
        VisitEntity visitEntity = new VisitEntity();
        if(visitTO == null){
            return visitEntity;
        }
        visitEntity.setTime(visitEntity.getTime());
        visitEntity.setDoctor(DoctorMapper.mapToEntity(visitTO.getDoctor()));
        visitEntity.setMedicalTreatments(MedicalTreatmentMapper.mapToEntity(visitTO.getMedicalTreatments()));

        return visitEntity;
    }

    public static VisitEntity mapTo(VisitTO visitTO) {
        if (visitTO == null) {
            return null;
        }
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setTime(visitTO.getTime());
        visitEntity.setDoctor(DoctorMapper.mapToEntity(visitTO.getDoctor()));
        return visitEntity;
    }
}
