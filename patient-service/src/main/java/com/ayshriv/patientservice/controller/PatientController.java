package com.ayshriv.patientservice.controller;

import com.ayshriv.patientservice.common.ApiResponse;
import com.ayshriv.patientservice.common.Constants;
import com.ayshriv.patientservice.common.CoreXStatus;
import com.ayshriv.patientservice.common.PatientConstants;
import com.ayshriv.patientservice.resources.dto.patient.CommonPatientRequest;
import com.ayshriv.patientservice.resources.dto.patient.PatientRequestDTO;
import com.ayshriv.patientservice.resources.dto.patient.PatientResponseDTO;
import com.ayshriv.patientservice.service.IPatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


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

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable String id, @RequestBody @Valid PatientRequestDTO patientRequestDTO) {
        LOGGER.info("Start: Updating patient with details - {}", patientRequestDTO);
        PatientResponseDTO patient = patientService.updatePatient(Long.parseLong(id),patientRequestDTO);
        LOGGER.info("Success: Patient updated with ID - {}", patient.getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(Constants.STATUS_SUCCESS,PatientConstants.PATIENT_UPDATED, null, patient));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPatient(@PathVariable String id) {
        LOGGER.info("Start: Getting patient with ID - {}", id);
        PatientResponseDTO patient = patientService.getPatient(Long.parseLong(id));
        LOGGER.info("Success: Patient fetched with ID - {}", patient.getId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(Constants.STATUS_SUCCESS,PatientConstants.PATIENT_FOUND, null, patient));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable String id) {
        LOGGER.info("Start: Deleting patient with ID - {}", id);
        patientService.deletePatient(Long.parseLong(id));
        LOGGER.info("Success: Patient deleted with ID - {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(Constants.STATUS_SUCCESS,PatientConstants.PATIENT_DELETED, null, null));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPatient(@RequestBody CommonPatientRequest commonPatientRequest) {
        LOGGER.info("Start: Getting all patients");
        CoreXStatus patient = patientService.getAllPatient(commonPatientRequest);
        LOGGER.info("Success: All patients fetched");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse(Constants.STATUS_SUCCESS,PatientConstants.PATIENT_FOUND,Map.of(
                        "total_pages", patient.getTotalPage(),
                        "total_record", patient.getTotalRecord()
                ),patient.getData()));
    }


}
