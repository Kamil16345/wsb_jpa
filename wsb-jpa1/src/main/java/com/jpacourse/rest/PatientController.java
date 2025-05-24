package com.jpacourse.rest;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientTO findById(@PathVariable Long id) {
        PatientTO patient = patientService.findById(id);
        if (patient != null) {
            return patient;
        }
        throw new EntityNotFoundException(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PatientTO> findAll() {
        return patientService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientTO create(@RequestBody PatientTO patientTO) {
        return patientService.save(patientTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientTO update(@PathVariable Long id, @RequestBody PatientTO patientTO) {
        patientTO.setId(id);
        return patientService.update(patientTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        patientService.deleteById(id);
    }

    @GetMapping("/visits-more-than/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientTO> findPatientsWithMoreVisitsThan(@PathVariable int amount) {
        return patientService.findPatientsWithMoreVisitsThan(amount);
    }

    @GetMapping("/joined-on")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientTO> findPatientsWhoJoinedOnGivenDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return patientService.findPatientsWhoJoinedOnGivenDate(date);
    }
}