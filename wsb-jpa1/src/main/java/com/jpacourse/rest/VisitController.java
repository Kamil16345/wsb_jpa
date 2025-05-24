package com.jpacourse.rest;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visit")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VisitTO findById(@PathVariable Long id) {
        VisitTO visit = visitService.findById(id);
        if (visit != null) {
            return visit;
        }
        throw new EntityNotFoundException(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VisitTO> findAll() {
        return visitService.findAll();
    }

    @GetMapping("/patient/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<VisitTO> findVisitsByPatientId(@PathVariable Long patientId) {
        return visitService.findVisitsByPatientId(patientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitTO create(@RequestBody VisitTO visitTO) {
        return visitService.save(visitTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VisitTO update(@PathVariable Long id, @RequestBody VisitTO visitTO) {
        visitTO.setId(id);
        return visitService.update(visitTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        visitService.deleteById(id);
    }
}