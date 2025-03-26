package com.ayshriv.patientservice.service;

import com.ayshriv.patientservice.common.CoreXStatus;
import com.ayshriv.patientservice.resources.dto.patient.CommonPatientRequest;
import com.ayshriv.patientservice.resources.dto.patient.PatientRequestDTO;
import com.ayshriv.patientservice.resources.dto.patient.PatientResponseDTO;
import java.util.List;

public interface IPatientService {

    /**
     * Creates a new patient and returns the created patient
     * @param patientRequestDTO contains the patient details
     * @return the created patient
     */
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);

    /**
     * Updates the patient with the given patientId and returns the updated patient
     * @param patientRequestDTO contains the patient details
     * @return the updated patient
     */
    PatientResponseDTO updatePatient(Long id,PatientRequestDTO patientRequestDTO);

    /**
     * Returns the patient with the given patientId
     * @param patientId the patientId to fetch
     * @return the patient with the given patientId
     */
    PatientResponseDTO getPatient(Long patientId);

    /**
     * Returns a list of all the patients
     * @param commonPatientRequest contains the pagination and sorting details
     * @return a list of all the patients
     */
    CoreXStatus getAllPatient(CommonPatientRequest commonPatientRequest);

    /**
     * Deletes the patient with the given patientId
     * @param patientId the patientId to delete
     * @return true if the patient is deleted successfully, false otherwise
     */
    void deletePatient(Long patientId);
}
