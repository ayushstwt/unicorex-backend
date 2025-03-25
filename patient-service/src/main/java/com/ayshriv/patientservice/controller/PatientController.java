package com.ayshriv.patientservice.controller;

import com.ayshriv.patientservice.common.ApiResponse;
import com.ayshriv.patientservice.common.Constants;
import com.ayshriv.patientservice.common.PatientConstants;
import com.ayshriv.patientservice.dto.patient.PatientRequestDTO;
import com.ayshriv.patientservice.dto.patient.PatientResponseDTO;
import com.ayshriv.patientservice.service.IPatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * This class is responsible for handling the rest end points for patients.
 * @author Ayush Shrivastava
 * @version 1.0
 */

@RestController
@RequestMapping("/api/patients")
@Validated
public class PatientController {

    private static final Logger LOGGER=LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private IPatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<?> createPatient(@RequestBody @Valid PatientRequestDTO patientRequestDTO) {
        LOGGER.info("Start: Creating patient with details - {}", patientRequestDTO);
        PatientResponseDTO patient = patientService.createPatient(patientRequestDTO);
        LOGGER.info("Success: Patient created with ID - {}", patient.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(Constants.STATUS_SUCCESS,PatientConstants.PATIENT_CREATED, null, patient));
    }


}
