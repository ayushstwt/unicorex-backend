package com.ayshriv.patientservice.service;

import com.ayshriv.patientservice.dto.patient.PatientRequestDTO;
import com.ayshriv.patientservice.dto.patient.PatientResponseDTO;
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
    PatientResponseDTO updatePatient(PatientRequestDTO patientRequestDTO);

    /**
     * Returns the patient with the given patientId
     * @param patientRequestDTO contains the patientId
     * @return the patient with the given patientId
     */
    PatientResponseDTO getPatient(PatientRequestDTO patientRequestDTO);

    /**
     * Returns a list of all the patients
     * @param patientRequestDTO
     * @return a list of all the patients
     */
    List<PatientResponseDTO> getAllPatient(PatientRequestDTO patientRequestDTO);

    /**
     * Deletes the patient with the given patientId
     * @param patientId the patientId to delete
     * @return true if the patient is deleted successfully, false otherwise
     */
    boolean deletePatient(Long patientId);
}
