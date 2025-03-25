package com.ayshriv.patientservice.mapper;

import com.ayshriv.patientservice.dto.patient.PatientRequestDTO;
import com.ayshriv.patientservice.dto.patient.PatientResponseDTO;
import com.ayshriv.patientservice.entity.Patient;
import com.ayshriv.patientservice.enums.Gender;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO mapToDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setFirstName(patient.getFirstName());
        patientResponseDTO.setLastName(patient.getLastName());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setPhoneNumber(patient.getPhoneNumber());
        patientResponseDTO.setGender(patient.getGender().toString());
        return patientResponseDTO;
    }

    public static Patient mapToEntity(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        patient.setFirstName(patientRequestDTO.getFirstName());
        patient.setLastName(patientRequestDTO.getLastName());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setPhoneNumber(patientRequestDTO.getPhoneNumber());
        patient.setGender(Gender.valueOf(patientRequestDTO.getGender()));
        return patient;
    }
}
