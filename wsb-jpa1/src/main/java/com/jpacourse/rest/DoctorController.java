package com.jpacourse.rest;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorTO findById(@PathVariable Long id) {
        DoctorTO doctor = doctorService.findById(id);
        if (doctor != null) {
            return doctor;
        }
        throw new EntityNotFoundException(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorEntity> findAll() {
        return doctorService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorTO create(@RequestBody DoctorTO doctorTO) {
        return doctorService.save(doctorTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorTO update(@PathVariable Long id, @RequestBody DoctorTO doctorTO) {
        doctorTO.setId(id);
        return doctorService.update(doctorTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        doctorService.deleteById(id);
    }
}
