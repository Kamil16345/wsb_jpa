package com.jpacourse.service.impl;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.VisitMapper;
import com.jpacourse.persistance.dao.VisitDao;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.service.VisitService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisitServiceImpl implements VisitService {

    private final VisitDao visitDao;

    @Autowired
    public VisitServiceImpl(VisitDao visit) {
        this.visitDao = visit;
    }

    @Override
    public VisitTO findById(final Long id) {
        VisitEntity visit = visitDao.findOne(id);
        return VisitMapper.mapToTO(visit);
    }

    @Override
    public List<VisitTO> findAll() {
        return visitDao.findAll().stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }

    public List<VisitTO> findVisitsByPatientId(final Long patientId) {
        return visitDao.findVisitsByPatientId(patientId).stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }
}